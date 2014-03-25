package com.eecs481.graceband;

//import android.content.Context;
//import android.net.Uri;
import com.eecs481.graceband.Track;
//import java.io.File;
import java.util.ArrayList;

public class AllTracks{
	public ArrayList<Track> tracks;
	
	public AllTracks(){
		tracks = new ArrayList<Track>();
		//initialize all available tracks
//		tracks.add(new Track(R.raw.tambourine, "Tambourine", R.drawable.selectorsnare_trackmenu, R.drawable.selectorsnare));
//		tracks.add(new Track(R.raw.piano, "Piano", R.drawable.selectorpiano_trackmenu, R.drawable.selectorpiano));
//		tracks.add(new Track(R.raw.bongo, "Bongos", R.drawable.selectorbass_trackmenu, R.drawable.selectorbass));
//		tracks.add(new Track(R.raw.vocal, "Vocals", R.drawable.selectorvocals_trackmenu, R.drawable.selectorvocals));
//		tracks.add(new Track(R.raw.guitar, "Guitar", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.bass_1, "Bass 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.bass_2, "Bass 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.basschord_1, "Bass Chords 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.basschord_2, "Bass Chords 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.basschord_3, "Bass Chords 3", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.harpsich_1, "Harpsichord 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.harpsich_2, "Harpsichord 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.hat_1, "Hat 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.hat_2, "Hat 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.hat_3, "Hat 3", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.kick_1, "Kick 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.kick_2, "Kick 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.kickclap_1, "Kick+Clap 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.kickclap_2, "Kick+Clap 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.kicksnare_1, "Kick+Snare 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.kicksnare_2, "Kick+Snare 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.piano_1, "Piano 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.piano_2, "Piano 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.piano_3, "Piano 3", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.piano_4, "Piano 4", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.piano_5, "Piano 5", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.piano_6, "Piano 6", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.sitar_1, "Sitar 1", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		tracks.add(new Track(R.raw.sitar_2, "Sitar 2", R.drawable.selectorbeatbox_trackmenu, R.drawable.selectorbeatbox));
		
	}
	
	public class TrackNotFoundException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3056777695578987462L;

		public TrackNotFoundException(String message_) {
	        super(message_);
	    }
	}
	
	public Track getTrackByName(String name_) throws TrackNotFoundException {
		for(int i = 0; i < tracks.size(); i++){
			if(tracks.get(i).get_name().equals(name_)){
				return tracks.get(i);
			}
		}
		throw new TrackNotFoundException("Track named \""+name_+"\" not found");
	}
}
