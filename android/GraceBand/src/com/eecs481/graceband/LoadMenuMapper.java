package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;

public class LoadMenuMapper {

	private Activity activity;
	private ArrayList<LinearLayout> fileList;

	private int pos;
	private LayoutParams lp;
	
	public LoadMenuMapper(Activity _activity, ArrayList<LinearLayout> _fileList) {
		activity = _activity;
		fileList = _fileList;
		pos = 0;
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.setMargins(10, 10, 10, 10);
	}
	
	public View getNextFocus(View currentView, MovementDirection direction) {
		View next = currentView;
		switch(direction) {
			case UP:
				if(pos > 0 || fileList.size() > 5) {
					pos--;
				}
				if(pos < 0){
					pos = 0;
					if(fileList.size() >= 5) {
						shiftListUp(activity);
					}
				}
				next = fileList.get(pos);
				break;
			case DOWN:
				if(pos < fileList.size()-1 || (fileList.size() > 5)) {
					pos++;
				}
				if(pos > 5) {
					pos = 5;
					shiftListDown(activity);
				}
				next = fileList.get(pos);
				break;
			default:
				break;
		}
		
		return next;
	}
	
	private void shiftListUp(Activity activity) {
		LinearLayout temp = fileList.get(fileList.size()-1);
		fileList.remove(fileList.size()-1);
		fileList.add(0,temp);
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.fileLayout);
		layout.removeAllViewsInLayout();
		for(int i=0; i<6 && i<fileList.size(); i++){
	    	layout.addView(fileList.get(i),lp);	    	
	    }
	}
	
	private void shiftListDown(Activity activity) {
		LinearLayout temp = fileList.get(0);
		fileList.remove(0);
		fileList.add(temp);
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.fileLayout);
		layout.removeAllViewsInLayout();
		for(int i=0; i<6 && i<fileList.size(); i++){
	    	layout.addView(fileList.get(i),lp);	    	
	    }
	}
}
