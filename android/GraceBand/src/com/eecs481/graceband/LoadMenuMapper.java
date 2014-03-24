package com.eecs481.graceband;

import android.app.Activity;
import android.view.View;

public class LoadMenuMapper {

	private Activity activity;
	private LoadMenu loadMenu;
	
	private int current;
	
	public LoadMenuMapper(Activity _activity) {
		activity = _activity;
	}
	
	public void setLoadMenu(LoadMenu _loadMenu) {
		loadMenu = _loadMenu;
	}
	
	public View getNextFocus(View currentView, MovementDirection direction) {
		View next = currentView;
		switch(direction) {
			case UP:
				//next = activity.findViewById();
				break;
			case DOWN:
				
				break;
			default:
				next = currentView;
				break;
		}
		
		return next;
	}
}
