package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnHoverListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Instruments {
	
	ArrayList<LinearLayout> buttonList;
	Context context;
	LinearLayout layout;
	int curLoc = 0;
	
	TrackHandle tracks;
	LinearLayout trackView;
	Activity activity;
	
	Instruments(Activity _activity, TrackHandle _t, View _view){
		System.out.println("creating Instruments");
		buttonList = new ArrayList<LinearLayout>();
		activity = _activity;
		context = activity.getApplicationContext();
		tracks = _t;
		trackView = (LinearLayout) _view;
	}
	
	// ADD SOUND FILE STUFF HERE?
	void createButton(Track track){
		System.out.println("creating button " + track.get_name());
		TrackButton tempButton = new TrackButton(context);
		//tempButton.setOnHoverListener(onHover);
		tempButton.setTrack(track);
		tempButton.setId(track.get_resid());
		tempButton.setPadding(46, 60, 0, 46);
		tempButton.setTag(track.get_name());
		TextView t = new TextView(context);
		t.setTextColor(Color.BLACK);
		t.setGravity(Gravity.CENTER_HORIZONTAL);
		t.setPadding(46, 0, 0, 0);
		t.setText(track.get_name());
		tempButton.setImageResource(track.getBeatMenuDrawable());
		
    	tempButton.setOnClickListener(new AddTrackListener(tracks, trackView, activity, this));
    	
    	tempButton.setBackgroundColor(Color.TRANSPARENT);
    	LinearLayout l = new LinearLayout(context);
    	l.setOrientation(LinearLayout.VERTICAL);
    	l.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	l.addView(tempButton);
    	l.addView(t);
    	buttonList.add(l);
	}
	
	void createScreen(){
		System.out.println("creating screen");
		layout = (LinearLayout) activity.findViewById(R.id.soundMenu);
		layout.removeAllViewsInLayout();
	    curLoc = 0;
	    for(LinearLayout button : buttonList){
	    	layout.addView(button);	    	
	    }
	}
}
