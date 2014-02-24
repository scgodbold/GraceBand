package com.eecs481.graceband;

import com.eecs481.graceband.Track;
import java.util.ArrayList;

public class AllTracks {
	public ArrayList<Track> tracks;
	
	private static AllTracks singleton = new AllTracks();
	
	public static AllTracks get_instance(){
		return singleton;
	}
	
	private AllTracks(){
		//initialize all available tracks
		
		
		
		
		
		
		
		
	}
	
	public class TrackNotFoundException extends Exception{
		TrackNotFoundException(){}
	}
	
	Track getTrackById(int soundId_) throws TrackNotFoundException {
		for(Track t : tracks){
			if(t.getSoundId() == soundId_){
				return t;
			}
		}
		throw new TrackNotFoundException();
	}
	

}
