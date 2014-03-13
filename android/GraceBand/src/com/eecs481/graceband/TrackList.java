package com.eecs481.graceband;

import java.util.ArrayList;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.eecs481.graceband.Track;

public class TrackList extends SoundPool {
	private int curPos;
	private boolean isPlaying;
	private ArrayList<Beat> Beats;
	
	public class Beat{
		Beat(int position_, int soundID_){
			position = position_;
			soundID = soundID_;
		}
		public int position;
		public int soundID;
		public int streamID;
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
		Beats.add(new Beat(curPos, this.load(context, t.get_resid(), 1)));
		return curPos++;
	}
	
}
