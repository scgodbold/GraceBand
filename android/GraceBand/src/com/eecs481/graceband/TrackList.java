package com.eecs481.graceband;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.eecs481.graceband.AllTracks.TrackNotFoundException;
import com.eecs481.graceband.Track;

public class TrackList extends SoundPool {
	private int curPos;
	private boolean isPlaying;
	private ArrayList<Beat> Beats;
	
	public class Beat{
		Beat(int position_, int soundID_, Track track_){
			position = position_;
			soundID = soundID_;
			track = track_;
		}
		public int position;
		public int soundID;
		public int streamID;
		public Track track;
	}
	
	private static TrackList singleton = new TrackList();
	
	public static TrackList get_instance(){
		return singleton;
	}
	
	private TrackList(){
		super(10, AudioManager.STREAM_MUSIC, 0);
		curPos = 0;
		isPlaying = false;
		Beats = new ArrayList<Beat>();
	}
	
	public void playAll(){
		if(isPlaying == false){
			for(int i = 0; i < Beats.size(); i++){
				this.play(Beats.get(i).soundID, 0.0f, 0.0f, 0, -1, 1f);
				this.play(Beats.get(i).soundID, 0.0f, 0.0f, 0, -1, 1f);
				Beats.get(i).streamID = this.play(Beats.get(i).soundID, 1.0f, 1.0f, 1, -1, 1.0f);
			}
			if(Beats.size() > 0){
				isPlaying = true;
			}
		}
	}
	
	public void stopAll(){
		if(isPlaying == true){
			for(int i = 0; i < Beats.size(); i++){
				this.stop(Beats.get(i).streamID);
			}
			isPlaying = false;
		}
	}
	
	public void removeTrack(int position_){
		for(int b = 0; b < Beats.size(); b++){
			if (Beats.get(b).position == position_){
				if(isPlaying == true){
					this.stop(Beats.get(b).streamID);
				}
				Beats.remove(Beats.get(b));
				if(Beats.size() == 0){
					isPlaying = false;
				}
			}
		}
	}
	
	public int addTrack(Context context, Track t){
		stopAll();
		Beats.add(new Beat(curPos, this.load(context, t.get_resid(), 1), t));
		return curPos++;
	}
	
	public void clearBeats() {
		Beats.clear();
	}
	
	public void save(Context context_) throws IOException{
		saveFile(context_, "savedfile");
	}
	
	public void load(Context context_) throws IOException, TrackNotFoundException{
		loadFile(context_, "savedfile");
	}
	
	public void saveFile(Context context_, String target_) throws IOException{
		FileOutputStream fos = null;
		
		//open the file for writing
		fos = context_.openFileOutput(target_, Context.MODE_PRIVATE);
	
		//write the name of each track to the file, separated by commas
		for(int i = 0; i < Beats.size(); i++){
				fos.write(Beats.get(i).track.get_name().getBytes());
				if(i != Beats.size()){
					fos.write(";".getBytes());
				}
		}
		
		fos.close();
	}
	
	public void loadFile(Context context_, String target_) throws IOException, TrackNotFoundException{
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(context_.openFileInput(target_)));
		
		String fileData;
		StringBuffer stringBuffer = new StringBuffer();                
		while ((fileData = inputReader.readLine()) != null) {
			stringBuffer.append(fileData + "\n");
		}
		
		inputReader.close();
		
		System.out.println("Read from file: "+fileData);
		if(fileData == null){
			System.out.println("Empty load file");
			return;
		}
		
		System.out.println("Tokens:");
		String tokens[] = fileData.split(";");
		for(int i = 0; i < tokens.length; i++){
			System.out.println(tokens[i]);
		}
		
		AllTracks a = new AllTracks();
		
		//build an ArrayList<Beat> from the tokens
		ArrayList<Beat> temp = new ArrayList<Beat>();
		for(int i = 0; i < tokens.length; i++){
			Track t = a.getTrackByName(tokens[i]);
			new Beat(curPos, this.load(context_, t.get_resid(), 1), t);
		}
		
		//if finished without exception, move data over
		Beats = temp;
	}
	
	public void deleteFile(Context context_, String target_){
		context_.deleteFile(target_);
	}
	
}
