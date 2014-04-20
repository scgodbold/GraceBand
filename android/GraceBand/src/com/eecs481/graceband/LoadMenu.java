package com.eecs481.graceband;

import java.io.IOException;
import java.util.ArrayList;

import com.eecs481.graceband.AllTracks.TrackNotFoundException;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.support.v4.app.NavUtils;

public class LoadMenu extends Activity {

	private ArrayList<String> songArray;
	private LoadMenuMapper map;
	
	private static double ZERO_TOLERANCE = .40;
	private static long TIME_TOLERANCE = 100;
	private boolean reset;
	private boolean lockTouch;
	private long previousEvent;
	
	private static ArrayList<LinearLayout> fileList;
	private LinearLayout fileLayout;
	static LayoutParams lp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        previousEvent =0;
        reset = true;
		lockTouch = false;
        
        songArray = TrackList.get_instance().getFileList(getBaseContext());
		songArray.add(0,"New Song");
		
        lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 10, 10, 10);
        fileLayout = (LinearLayout)findViewById(R.id.fileLayout);
		fileList = new ArrayList<LinearLayout>();
		for(int i = 0; i < songArray.size(); i++){
			addFile(songArray.get(i), i);
		}
		

		fileLayout.removeAllViewsInLayout();
		for(int i=0; i<11 && i<fileList.size(); i++){
	    	fileLayout.addView(fileList.get(i),lp);	    	
	    }

	    fileList.get(0).setFocusableInTouchMode(true);
		fileList.get(0).setFocusable(true); 
	    fileList.get(0).requestFocus();
	    map = new LoadMenuMapper(this,fileList);
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) public void addFile(String name, int i){
		LinearLayout tempButton = new LinearLayout(getBaseContext());
		
		TextView t = new TextView(getBaseContext());
		t.setText(name);
		t.setTextSize(24);
		t.setTypeface(Typeface.DEFAULT_BOLD);
		t.setTextColor();
		
		ColorStateList l = R.color.loadmenu_text_color;
		
		Drawable d = getResources().getDrawable(R.drawable.selector_loadmenu);
		
		tempButton.setId(i);
		tempButton.setPadding(10,25,10,25);
		tempButton.setFocusableInTouchMode(false);
        tempButton.setFocusable(false);        
        tempButton.setNextFocusDownId(tempButton.getId());
        tempButton.setNextFocusLeftId(tempButton.getId());
        tempButton.setNextFocusRightId(tempButton.getId());
        tempButton.setNextFocusUpId(tempButton.getId());
        tempButton.setBackground(d);
        tempButton.setGravity(Gravity.CENTER);
        
        tempButton.addView(t);

        fileLayout.addView(tempButton, lp);

    	fileList.add(tempButton);
	}
	
	private void loadSong(View v)
	{
		TextView temp = (TextView) v;
		//TrackList.get_instance().loadFile(getBaseContext(), temp.getText().toString());
		Intent intent = new Intent(getBaseContext(), BeatsEditor.class);
		intent.putExtra("saveFileName", temp.getText().toString());
		startActivity(intent);
	}
	public boolean onGenericMotionEvent(MotionEvent event)
    {
    	float x = event.getX();
    	float y = event.getY();
    	
    	long diff = event.getEventTime() - previousEvent;
    	previousEvent = event.getEventTime();
    	
    	boolean xPos = x > 0;
    	boolean yPos = y > 0;
    	
    	x = (x > 0) ? x : -x;
    	y = (y > 0) ? y : -y;
    	
    	if(x < ZERO_TOLERANCE && y < ZERO_TOLERANCE)
    	{
    		reset = true;
    	}
    	else if(reset && diff > TIME_TOLERANCE)
    	{    		
    		View v,w;
    		w = getCurrentFocus();
    		v = w;
    		if(x < y)
    		{
    			reset = false;
    			if(yPos)
    			{
    				v = map.getNextFocus(w, MovementDirection.DOWN);
    			}
    			else
    			{
    				v = map.getNextFocus(w, MovementDirection.UP);
    			}
    		}
    		if(v.getId() != w.getId())
    		{
    			v.setFocusable(true);
    			v.setFocusableInTouchMode(true);
    			v.requestFocus();
    			w.setFocusable(false);
    			w.setFocusableInTouchMode(false);
    		}
    	}
    	return true;
    }	
	
    public boolean onTouchEvent(MotionEvent event)
    {
    	
    	if(event.getAction() == MotionEvent.ACTION_DOWN)
    	{
    		if(!lockTouch)
    		{
	    		lockTouch = true;
	    		System.out.println("Down Event");
	    		LinearLayout current = (LinearLayout) getCurrentFocus();
	    		loadSong(current.getChildAt(0));
    		}
    	}
    	else if(event.getAction() == MotionEvent.ACTION_UP)
    	{
    		lockTouch = false;
    		System.out.println("Up Event");
    	}
		return true;
    }
}
