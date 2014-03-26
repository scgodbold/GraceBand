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
import android.view.View.OnClickListener;
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

	private ArrayList<String> songArray;
	private LoadMenuMapper map;
	
	private static double ZERO_TOLERANCE = .97;
	private static long TIME_TOLERANCE = 20;
	private boolean reset;
	private long previousEvent;
	
	private static ArrayList<Button> fileList;
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
		
        songArray = TrackList.get_instance().getFileList(getBaseContext());
		songArray.add(0,"New Song");
		
        lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        fileLayout = (LinearLayout)findViewById(R.id.fileLayout);
		fileList = new ArrayList<Button>();
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
        tempButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Button temp = (Button) v;
				//TrackList.get_instance().loadFile(getBaseContext(), temp.getText().toString());
				Intent intent = new Intent(getBaseContext(), BeatsEditor.class);
				intent.putExtra("saveFileName", temp.getText().toString());
				startActivity(intent);
			}
		});

        fileLayout.addView(tempButton, lp);

    	fileList.add(tempButton);
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
	
	static public void shiftListUp(Activity activity) {
		Button temp = fileList.get(fileList.size()-1);
		fileList.remove(fileList.size()-1);
		fileList.add(0,temp);
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.fileLayout);
		layout.removeAllViewsInLayout();
		for(int i=0; i<11 && i<fileList.size(); i++){
	    	layout.addView(fileList.get(i),lp);	    	
	    }
	}
	
	static public void shiftListDown(Activity activity) {
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
