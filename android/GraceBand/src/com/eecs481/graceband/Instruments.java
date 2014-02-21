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
	Button larrow;
	Button rarrow;
	
	TrackHandle tracks;
	LinearLayout trackView;
	Activity activity;
	
	Instruments(Activity _activity, TrackHandle _t, View _view){
		System.out.println("creating Instruments");
		buttonList = new ArrayList<ImageButton>();
		activity = _activity;
		context = activity.getApplicationContext();
		larrow = new Button(context);
		rarrow = new Button(context);
		larrow.setText("L");
		larrow.setTextColor(Color.BLACK);
		rarrow.setText("R");
		rarrow.setTextColor(Color.BLACK);
		larrow.setOnClickListener(leftClick);
		rarrow.setOnClickListener(rightClick);
		tracks = _t;
		trackView = (LinearLayout) _view;
	}
	
	// ADD SOUND FILE STUFF HERE?
	void createButton(String name){
		System.out.println("creating button " + name);
		ImageButton tempButton = new ImageButton(context);
		tempButton.setOnHoverListener(onHover);
		tempButton.setId(buttonList.size());
		tempButton.setTag(name);
		
		if(name.contentEquals("Piano")){
			tempButton.setImageResource(R.drawable.piano);
		}
		else if(name.contentEquals("Snare Drum")){
			tempButton.setImageResource(R.drawable.snare);
		}
		else if(name.contentEquals("Bass Drum")){
			tempButton.setImageResource(R.drawable.bass);
		}
		else if(name.contentEquals("Vocals")){
			tempButton.setImageResource(R.drawable.vocals);
		}
		else{
			tempButton.setImageResource(R.drawable.beatbox);
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
	    //layout.addView(larrow);
	    for(ImageButton button : buttonList){
	    	layout.addView(button);	    	
	    }
	    //layout.addView(rarrow);
	}
	
	void left(){
		layout.removeAllViewsInLayout();
		layout.addView(larrow);
		curLoc -= 1;
		if(curLoc < 0) curLoc = buttonList.size()-1;
		int counter = 0;
		int curLocTemp = curLoc;
	    for(int i = curLoc; i < curLocTemp+3; i++, counter++){
	    	if(i == buttonList.size()) {
	    		i = 0;
	    		curLocTemp = -counter;
	    	}
	    	ImageButton button = buttonList.get(i);
	    	layout.addView(button);	    	
	    }
	    layout.addView(rarrow);
	}
	
	void right(){
		layout.removeAllViewsInLayout();
		layout.addView(larrow);
		curLoc += 1;
		if(curLoc == buttonList.size()) curLoc = 0;
		int counter = 0;
		int curLocTemp = curLoc;
	    for(int i = curLoc; i < curLocTemp+3; i++, counter++){
	    	if(i == buttonList.size()) {
	    		i = 0;
	    		curLocTemp = -counter;
	    	}
	    	ImageButton button = buttonList.get(i);
	    	layout.addView(button);	    	
	    }
	    layout.addView(rarrow);
	}
	
	final OnClickListener leftClick = new OnClickListener() {
        public void onClick(final View v) {
        	left();
        }
    };
    
    final OnClickListener rightClick = new OnClickListener() {
        public void onClick(final View v) {
        	right();
        }
    };
    final OnHoverListener onHover = new OnHoverListener() {
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
    };
}
