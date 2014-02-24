package com.eecs481.graceband;

import android.content.Context;
import android.net.Uri;

import com.eecs481.graceband.Track;

import java.io.File;
import java.util.ArrayList;

public class AllTracks {
	public ArrayList<Track> tracks;

	/*private static AllTracks singleton = new AllTracks();

	public static AllTracks get_instance(){
		return singleton;
	}*/

	public AllTracks(Context context){
		//initialize all available tracks	
		tracks = new ArrayList<Track>();
		tracks.add(new Track(context, R.raw.ride_bell));
		tracks.add(new Track(context, R.raw.kick));




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
