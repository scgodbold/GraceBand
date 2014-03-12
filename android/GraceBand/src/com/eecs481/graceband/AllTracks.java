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
		tracks.add(new Track(R.raw.tambourine, "Tambourine"));
		tracks.add(new Track(R.raw.piano, "Piano"));
		tracks.add(new Track(R.raw.bongo, "Bongos"));
		tracks.add(new Track(R.raw.vocal, "Vocals"));
		tracks.add(new Track(R.raw.guitar, "Guitar"));
	}
}
