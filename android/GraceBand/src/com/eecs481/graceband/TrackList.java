package com.eecs481.graceband;

import com.eecs481.graceband.Track;
import java.util.ArrayList;

public class TrackList {
	TrackList(){}
	
	public ArrayList<Track> tracks;
	
	public void playAll(){
		for(Track t : tracks){
			t.play();
		}
	}
	
	public void pauseAll(){
		for(Track t : tracks){
			t.pause();
		}
	}
	
	public void stopAll(){
		for(Track t : tracks){
			t.stop();
		}
	}
	
	public void removeTrack(int pos){
		tracks.remove(pos);
	}
	
	public void addTrack(Track t){
		tracks.add(t);
	}
	
}
