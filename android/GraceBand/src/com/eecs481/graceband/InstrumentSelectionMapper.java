package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

public class InstrumentSelectionMapper {

	private Activity activity;
	private AllTracks allTracks;
	private TrackHandle trackHandle;
	
	ArrayList<LinearLayout> buttonList;
	
	private int current;
	private boolean cancel;
	
	public InstrumentSelectionMapper(Activity _activity, AllTracks _allTracks) {
		activity = _activity;
		allTracks = _allTracks;
		current = 2;
        cancel = false;
	}
	
	public void setButtonList(ArrayList<LinearLayout> _buttonList){
		buttonList = _buttonList;
	}
	
	public void setTrackHandle(TrackHandle _trackHandle) {
		trackHandle = _trackHandle;
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
					shiftListRight();
				}
				break;
			case RIGHT:
				if(!cancel) {
					current++;
					if(current >= allTracks.tracks.size()) {
						current = 0;
					}
					next = activity.findViewById(allTracks.tracks.get(current).get_resid());
					shiftListLeft();
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
		current = 2;
		System.out.println("THIS IS THE CURRENT " + current);
		return activity.findViewById(allTracks.tracks.get(current).get_resid());
	}
	
	public void shiftListRight() {
		LinearLayout temp = buttonList.get(buttonList.size()-1);
		buttonList.remove(buttonList.size()-1);
		buttonList.add(0,temp);
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.soundMenu);
		layout.removeAllViewsInLayout();
		for(int i=0; i<5; i++){
	    	layout.addView(buttonList.get(i));	    	
	    }
	}
	
	public void shiftListLeft() {
		LinearLayout temp = buttonList.get(0);
		buttonList.remove(0);
		buttonList.add(temp);
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.soundMenu);
		layout.removeAllViewsInLayout();
		for(int i=0; i<5; i++){
	    	layout.addView(buttonList.get(i));	    	
	    }
	}
}
