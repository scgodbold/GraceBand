package com.eecs481.graceband;

import android.app.Activity;
import android.view.View;

public class InstrumentSelectionMapper {

	private Activity activity;
	private AllTracks allTracks;
	
	private int current;
	private boolean cancel;
	
	public InstrumentSelectionMapper(Activity _activity, AllTracks _allTracks) {
		activity = _activity;
		allTracks = _allTracks;
		current = (int) Math.ceil((double)allTracks.tracks.size()/2.0);
        cancel = false;
	}
	
	public View getNextFocus(View currentView, MovementDirection direction) {
		View next = currentView;
		switch(direction) {
			case UP:
				if(cancel) {
					cancel = false;
					next = activity.findViewById(allTracks.tracks.get(current).get_resid());
				}
				break;
			case DOWN:
				if(!cancel) {
					cancel = true;
			        next = activity.findViewById(R.id.cancel);
				}
				break;
			case LEFT:
				if(!cancel) {
					current--;
					if(current < 0) {
						current = allTracks.tracks.size()-1;
					}
					next = activity.findViewById(allTracks.tracks.get(current).get_resid());
				}
				break;
			case RIGHT:
				if(!cancel) {
					current++;
					if(current >= allTracks.tracks.size()) {
						current = 0;
					}
					next = activity.findViewById(allTracks.tracks.get(current).get_resid());
				}
				break;
			default:
				next = currentView;
				break;
		}
		
		return next;
	}

	public View getCurrent()
	{
		current = allTracks.tracks.size()/2;
		System.out.println("THIS IS THE CURRENT " + current);
		return activity.findViewById(allTracks.tracks.get(current).get_resid());
	}
}
