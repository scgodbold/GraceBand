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
		tracks.add(new Track(R.raw.tambourine, "Tambourine", R.drawable.selectorsnare_trackmenu, R.drawable.selectorsnare));
		tracks.add(new Track(R.raw.piano, "Piano", R.drawable.selectorpiano_trackmenu, R.drawable.selectorpiano));
		tracks.add(new Track(R.raw.bongo, "Bongos", R.drawable.selectorbass_trackmenu, R.drawable.selectorbass));
		tracks.add(new Track(R.raw.vocal, "Vocals", R.drawable.selectorvocals_trackmenu, R.drawable.selectorvocals));
		tracks.add(new Track(R.raw.guitar, "Guitar", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
	}
}
