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
	
	public LoadMenuMapper(Activity _activity, ArrayList<Button> _fileList) {
		activity = _activity;
		fileList = _fileList;
		current = 0;
	}
	
	public View getNextFocus(View currentView, MovementDirection direction) {
		View next = currentView;
		switch(direction) {
			case UP:
				if(current - 1 >= 0){
					current--;
					next = fileList.get(current);
					if(current%10 == 0 && current != 0){
						LoadMenu.shiftListUp(activity);
					}
				}
				break;
			case DOWN:
				if(current + 1 < fileList.size()){
					current++;
					next = fileList.get(current);
					if(current%11 == 0 && current != fileList.size() - 1) {
						LoadMenu.shiftListDown(activity);
					}
				}
				break;
			default:
				break;
		}
		
		return next;
	}
}
