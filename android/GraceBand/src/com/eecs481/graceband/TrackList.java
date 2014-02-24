package com.eecs481.graceband;

import com.eecs481.graceband.Track;
import java.util.ArrayList;

public class TrackList {
	private int curPos;
	private AllTracks list;
	private static TrackList singleton = new TrackList();

	public ArrayList<CurrentTrack> tracks;
	
	public void setList(AllTracks _list){
		list = _list;
	}
	
	public static TrackList get_instance(){
		return singleton;
	}

	private TrackList(){
		tracks = new ArrayList<CurrentTrack>();
		curPos = 0;
	}

	public class CurrentTrack{
		CurrentTrack(int position_, Track t_){
			position = position_;
			t = t_;
		}
		public int position;
		public Track t;
	}

	public void playAll(){
		System.out.println("playing");
		for(CurrentTrack ct : tracks){
			ct.t.play();
		}
	}

	public void pauseAll(){
		for(CurrentTrack ct : tracks){
			ct.t.pause();
		}
	}

	public void stopAll(){
		for(CurrentTrack ct : tracks){
			ct.t.stop();
		}
	}

	public void removeTrack(int position_){
		for(CurrentTrack ct : tracks){
			if (ct.position == position_){
				tracks.remove(ct);
			}
		}
	}

	public int addTrack(int soundId_){
		try{
			tracks.add(new CurrentTrack(curPos, list.getTrackById(soundId_)));
		}
		catch(Exception e){
			return -1;
		}
		return curPos++;
	}

}
