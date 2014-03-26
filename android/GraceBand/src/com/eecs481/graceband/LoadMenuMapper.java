package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class LoadMenuMapper {

	private Activity activity;
	private ArrayList<Button> fileList;
	
	private int current;
	private int pos;
	
	public LoadMenuMapper(Activity _activity, ArrayList<Button> _fileList) {
		activity = _activity;
		fileList = _fileList;
		current = 0;
		pos = 0;
	}
	
	public View getNextFocus(View currentView, MovementDirection direction) {
		View next = currentView;
		switch(direction) {
			case UP:
				pos--;
				current--;
				if(pos <= 0){
					pos = 0;
					current = 0;
					LoadMenu.shiftListUp(activity);
				}
				next = fileList.get(current);
				break;
			case DOWN:
				pos++;
				current++;
				if(pos >= 10) {
					pos = 10;
					current = 10;
					LoadMenu.shiftListDown(activity);
				}
				next = fileList.get(current);
				break;
			default:
				break;
		}
		
		return next;
	}
}
