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
		current = allTracks.tracks.size()/2;
        cancel = false;
        activity.findViewById(allTracks.tracks.get(current).get_resid()).setFocusableInTouchMode(true);
        activity.findViewById(allTracks.tracks.get(current).get_resid()).setFocusable(true);
        activity.findViewById(allTracks.tracks.get(current).get_resid()).requestFocus();
        for(int i = 0; i < current; i++) {
	        activity.findViewById(allTracks.tracks.get(i).get_resid()).setFocusableInTouchMode(false);
	        activity.findViewById(allTracks.tracks.get(i).get_resid()).setFocusable(false);
        }
        for(int i = current+1; i < allTracks.tracks.size(); i++) {
	        activity.findViewById(allTracks.tracks.get(i).get_resid()).setFocusableInTouchMode(false);
	        activity.findViewById(allTracks.tracks.get(i).get_resid()).setFocusable(false);
        }
        
        activity.findViewById(R.id.cancel).setFocusableInTouchMode(false);
        activity.findViewById(R.id.cancel).setFocusable(false);
	}
	
	public View focusInstrument(View currentView, MovementDirection direction) {
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
	
}
