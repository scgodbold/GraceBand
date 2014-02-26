package com.eecs481.graceband;

import android.content.Context;
import android.net.Uri;

import com.eecs481.graceband.Track;

import java.io.File;
import java.util.ArrayList;

public class AllTracks{
	public ArrayList<Track> tracks;
	
	public AllTracks(){
		tracks = new ArrayList<Track>();
		//initialize all available tracks
		tracks.add(new Track(R.raw.kick, "Kick drum"));
		tracks.add(new Track(R.raw.ride_bell, "Ride bell"));
		tracks.add(new Track(R.raw.bongo, "Bongo"));
		tracks.add(new Track(R.raw.drums, "Drums"));
		tracks.add(new Track(R.raw.guitar, "Guitar"));
	}
}

/*
public class AllTracks {
	public ArrayList<Track> tracks;
	
	private static AllTracks singleton = new AllTracks();
	
	public static AllTracks get_instance(){
		return singleton;
	}
	
	private AllTracks(){
		//initialize all available tracks
		
	}
	
	public AllTracks(Context context){
		//initialize all available tracks	
		tracks = new ArrayList<Track>();
		tracks.add(new Track(context, R.raw.kick));
		tracks.add(new Track(context, R.raw.ride_bell));

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
*/