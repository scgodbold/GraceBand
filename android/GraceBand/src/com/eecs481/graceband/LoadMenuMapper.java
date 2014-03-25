package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

public class LoadMenuMapper {

	private Activity activity;
	private ListView listView;
	private ArrayList<String> songArray;
	
	private int current;
	
	public LoadMenuMapper(Activity _activity, ArrayList<String> _songArray,ListView _lv) {
		activity = _activity;
		songArray = _songArray;
		listView = _lv;
		current = 0;
	}
	
	public View getNextFocus(View currentView, MovementDirection direction) {
		View next = currentView;
		switch(direction) {
			case UP:
				if(current - 1 >= 0){
					current--;
					next = listView.getChildAt(current);
				}
				break;
			case DOWN:
				if(current + 1 < songArray.size()){
					current++;
					next = listView.getChildAt(current);
				}
				break;
			default:
				break;
		}
		
		return next;
	}
}
