package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;

public class LoadMenuMapper {

	private Activity activity;
	private LoadMenu loadMenu;
	private ArrayList<String> songArray;
	
	private int current;
	
	public LoadMenuMapper(Activity _activity) {
		activity = _activity;
		current = 0;
	}
	
	public void setLoadMenu(LoadMenu _loadMenu) {
		loadMenu = _loadMenu;
	}
	
	public void setSongArray(ArrayList<String> _songArray) {
		songArray = _songArray;
	}
	
	public View getNextFocus(View currentView, MovementDirection direction) {
		View next = currentView;
		switch(direction) {
			case UP:
				
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
