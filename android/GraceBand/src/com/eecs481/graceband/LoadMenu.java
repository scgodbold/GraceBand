package com.eecs481.graceband;

import java.io.IOException;
import java.util.ArrayList;

import com.eecs481.graceband.AllTracks.TrackNotFoundException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.view.View;
import android.support.v4.app.NavUtils;

public class LoadMenu extends Activity {

	//private ListView lv;
	//private LoadMenuAdapter adapter;
	private ArrayList<String> songArray;
	private LoadMenuMapper map;
	
	private static double ZERO_TOLERANCE = .97;
	private static long TIME_TOLERANCE = 20;
	private boolean reset;
	private long previousEvent;
	
	private ArrayList<LinearLayout> fileList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
		songArray = TrackList.get_instance().getFileList(getBaseContext());
		songArray.add("New Song");
		
		
		fileList = new ArrayList<LinearLayout>();
		for(int i = 0; i < songArray.size(); i++){
			addFile(songArray.get(i), i);
		}
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.loadMenu);
		layout.removeAllViewsInLayout();
	    for(LinearLayout button : fileList){
	    	layout.addView(button);	    	
	    }
	    
	    map = new LoadMenuMapper(this,fileList);
	    
		setupActionBar();
	}
	
	public void addFile(String name, int i){
		Button tempButton = new Button(getBaseContext());
		//tempButton.setOnHoverListener(onHover);
		tempButton.setId(i);
		tempButton.setPadding(0,0,0,0);
		tempButton.setText(name);
		tempButton.setFocusableInTouchMode(false);
        tempButton.setFocusable(false);        
        tempButton.setNextFocusDownId(tempButton.getId());
        tempButton.setNextFocusLeftId(tempButton.getId());
        tempButton.setNextFocusRightId(tempButton.getId());
        tempButton.setNextFocusUpId(tempButton.getId());
        tempButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button temp = (Button) v;
				try {
					TrackList.get_instance().loadFile(getBaseContext(), (String) temp.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TrackNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	LinearLayout l = new LinearLayout(getBaseContext());
    	l.setOrientation(LinearLayout.VERTICAL);
    	l.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	l.addView(tempButton);
    	fileList.add(l);
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
    		if(x >= y)
    		{
    			reset = false;
    			if(xPos)
    			{
    				v = map.getNextFocus(w, MovementDirection.RIGHT);
    			}
    			else
    			{
    				v = map.getNextFocus(w, MovementDirection.LEFT);
    			}
    		}
    		else
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

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
