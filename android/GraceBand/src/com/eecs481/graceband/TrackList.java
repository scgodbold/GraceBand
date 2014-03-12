package com.eecs481.graceband;

import java.util.ArrayList;

import android.R.bool;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.eecs481.graceband.Track;

public class TrackList extends SoundPool {
	private int curPos;
	private boolean isPlaying;
	
	public class CurrentTrack{
		CurrentTrack(int position_, int soundID_){
			position = position_;
			soundID = soundID_;
		}
		public int position;
		public int soundID;
	}
	
	private ArrayList<CurrentTrack> Beats;
	
	public class CurrentStream{
		CurrentStream(int streamID_, int position_){
			position = position_;
			streamID = streamID_;
		}
		public int streamID;
		public int position;
	}
	
	private ArrayList<CurrentStream> Streams;
	
	private static TrackList singleton = new TrackList();
	
	public static TrackList get_instance(){
		return singleton;
	}
	
	private TrackList(){
		super(10, AudioManager.STREAM_MUSIC, 0);
		Beats = new ArrayList<CurrentTrack>();
		Streams = new ArrayList<CurrentStream>();
		curPos = 0;
		isPlaying = false;
	}
	
	public void playAll(){
		System.out.println("Called playAll()");
		if(isPlaying == false){
			System.out.println("(Not playing)");
			for(int i = 0; i < Beats.size(); i++){
				this.play(Beats.get(i).soundID, 0.0f, 0.0f, 0, -1, 1f);
				this.play(Beats.get(i).soundID, 0.0f, 0.0f, 0, -1, 1f);
				int thisstreamid = this.play(Beats.get(i).soundID, 1.0f, 1.0f, 1, -1, 1.0f);
				Streams.add(new CurrentStream(thisstreamid, Beats.get(i).position));
				System.out.println("Play stream #"+thisstreamid+" (pos "+Beats.get(i).position+")");
			}
			if(Beats.size() > 0){
				isPlaying = true;
			}
		}
	}
	
	/*
	public void pauseAll(){
		for(int i = 0; i < Streams.size(); i++){
			System.out.println("pause streamID "+Streams.get(i).streamID);
			this.pause(Streams.get(i).streamID);
		}
	}
	*/
	
	public void stopAll(){
		System.out.println("Called stopAll()");
		if(isPlaying == true){
			System.out.println("(Playing)");
			for(int i = 0; i < Streams.size(); i++){
				this.stop(Streams.get(i).streamID);
				System.out.println("Stop stream #"+Streams.get(i).streamID+" (pos "+Streams.get(i).position+")");
			}
			isPlaying = false;
		}
		Streams.clear();
	}
	
	public void removeTrack(int position_){
		System.out.println("Attempting to remove beat at position "+position_+":");
		for(int b = 0; b < Beats.size(); b++){
			if (Beats.get(b).position == position_){
				System.out.println("Found a beat at that position");
				
				if(isPlaying == true){
					System.out.println("(Playing)");
					for(int s = 0; s < Streams.size(); s++){
						if(Streams.get(s).position == position_){
							System.out.println("Found a stream at that position.");
							System.out.println("Stop stream #"+Streams.get(s).streamID+" (pos "+Streams.get(s).position+")");
							this.stop(Streams.get(s).streamID);
							System.out.println("Removing the stream");
							Streams.remove(Streams.get(s));
							break;
						}
					}
				}
				System.out.println("Removing the beat");
				Beats.remove(Beats.get(b));
				
				if(Beats.size() == 0){
					System.out.println("All beats removed");
					Streams.clear();
					isPlaying = false;
				}
			}
		}
	}
	
	public int addTrack(Context context, int resid){
		boolean resume = isPlaying;
		if(isPlaying){
			stopAll();
		}
		Beats.add(new CurrentTrack(curPos, this.load(context, resid, 1)));
		return curPos++;
	}

	public void clearBeats(){
		Beats.clear();
	}
	
}
