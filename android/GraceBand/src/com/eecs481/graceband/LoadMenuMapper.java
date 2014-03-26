package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

public class LoadMenuMapper {

	private Activity activity;
	private ArrayList<Button> fileList;
	
	private int current;
	private int pos;
	private LayoutParams lp;
	
	public LoadMenuMapper(Activity _activity, ArrayList<Button> _fileList) {
		activity = _activity;
		fileList = _fileList;
		current = 0;
		pos = 0;
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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
					shiftListUp(activity);
				}
				next = fileList.get(current);
				break;
			case DOWN:
				pos++;
				current++;
				if(pos >= 10) {
					pos = 10;
					current = 10;
					shiftListDown(activity);
				}
				next = fileList.get(current);
				break;
			default:
				break;
		}
		
		return next;
	}
	
	private void shiftListUp(Activity activity) {
		Button temp = fileList.get(fileList.size()-1);
		fileList.remove(fileList.size()-1);
		fileList.add(0,temp);
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.fileLayout);
		layout.removeAllViewsInLayout();
		for(int i=0; i<11 && i<fileList.size(); i++){
	    	layout.addView(fileList.get(i),lp);	    	
	    }
	}
	
	private void shiftListDown(Activity activity) {
		Button temp = fileList.get(0);
		fileList.remove(0);
		fileList.add(temp);
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.fileLayout);
		layout.removeAllViewsInLayout();
		for(int i=0; i<11 && i<fileList.size(); i++){
	    	layout.addView(fileList.get(i),lp);	    	
	    }
	}
}
