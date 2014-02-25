package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Instruments {
	
	ArrayList<ImageButton> buttonList;
	Context context;
	LinearLayout layout;
	int curLoc = 0;
	
	TrackHandle tracks;
	LinearLayout trackView;
	Activity activity;
	
	Instruments(Activity _activity, TrackHandle _t, View _view){
		System.out.println("creating Instruments");
		buttonList = new ArrayList<ImageButton>();
		activity = _activity;
		context = activity.getApplicationContext();
		tracks = _t;
		trackView = (LinearLayout) _view;
	}
	
	// ADD SOUND FILE STUFF HERE?
	void createButton(String name, int id){
		System.out.println("creating button " + name);
		ImageButton tempButton = new ImageButton(context);
		//tempButton.setOnHoverListener(onHover);
		tempButton.setId(id);
		tempButton.setPadding(46, 60, 0, 46);
		tempButton.setTag(name);
		
		// This makes the Track Selection Menu!!!!!
		if(name.contentEquals("Piano")){
			tempButton.setImageResource(R.drawable.selectorpiano_trackmenu);
		}
		else if(name.contentEquals("Snare Drum")){
			tempButton.setImageResource(R.drawable.selectorsnare_trackmenu);
		}
		else if(name.contentEquals("Bass Drum")){
			tempButton.setImageResource(R.drawable.selectorbass_trackmenu);
		}
		else if(name.contentEquals("Vocals")){
			tempButton.setImageResource(R.drawable.selectorvocals_trackmenu);
		}
		else{
			tempButton.setImageResource(R.drawable.selectorbeatbox_trackmenu);
		}
		
    	tempButton.setOnClickListener(new AddTrackListener(tracks, trackView, activity, this));
    	tempButton.setBackgroundColor(Color.TRANSPARENT);
		buttonList.add(tempButton);
	}
	
	void createScreen(){
		System.out.println("creating screen");
		layout = (LinearLayout) activity.findViewById(R.id.soundMenu);
		layout.removeAllViewsInLayout();
	    curLoc = 0;
	    for(ImageButton button : buttonList){
	    	layout.addView(button);	    	
	    }
	}

    /*final OnHoverListener onHover = new OnHoverListener() {
		@Override
		public boolean onHover(View v, MotionEvent event) {
			layout.removeAllViews();
			switch (v.getId()) {
				case 0:
				    for(int i = 3; i<buttonList.size(); i++){
				    	layout.addView(v);	    	
				    }
				    for(int i = 0; i<3; i++){
				    	layout.addView(v);	    	
				    }
		            break;
		        case 1:
				    for(int i = 4; i<buttonList.size(); i++){
				    	layout.addView(v);	    	
				    }
				    for(int i = 0; i<4; i++){
				    	layout.addView(v);	    	
				    }
		            break;
		        case 2:
				    for(int i = 0; i<buttonList.size(); i++){
				    	layout.addView(v);	    	
				    }
		            break;
		        case 3:
				    for(int i = 1; i<buttonList.size(); i++){
				    	layout.addView(v);	    	
				    }
				    for(int i = 0; i<1; i++){
				    	layout.addView(v);	    	
				    }
		            break;
		        default:
				    for(int i = 2; i<buttonList.size(); i++){
				    	layout.addView(v);	    	
				    }
				    for(int i = 0; i<2; i++){
				    	layout.addView(v);	    	
				    }
		            break;
			}
			return true;
		}	
    };*/
}
