package com.eecs481.graceband;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BeatsEditor extends Activity {

	TrackHandle t;	
	View trackView;
	
	private static double ZERO_TOLERANCE = .97;
	private static long TIME_TOLERANCE = 20;
	private boolean reset;
	private long previousEvent;
	private SongEditorMapper songMapper;
	public InstrumentSelectionMapper instrumentMap;
	public AllTracks allTracks;
	static public boolean instrumentMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beats_editor);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        final ImageButton playButton = (ImageButton) findViewById(R.id.play);
        final ImageButton pauseButton = (ImageButton) findViewById(R.id.stop);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.saveButton);
        final Button cancelButton = (Button) findViewById(R.id.cancel);
        final ImageButton backButton = (ImageButton) findViewById(R.id.back);
    
        allTracks = new AllTracks();
        instrumentMap = new InstrumentSelectionMapper(this, allTracks);
        
        t = new TrackHandle(this.getApplicationContext(), this, instrumentMap);
        trackView = findViewById(R.id.tracks);
        
        backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TrackList.get_instance().clearBeats();
				TrackList.get_instance().stopAll();
				finish();
			}
		});
        playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TrackList.get_instance().playAll();
			}
		});
        pauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TrackList.get_instance().stopAll();
			}
		});
        
        saveButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {

        	}
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				BeatsEditor.instrumentMenu = false;
				findViewById(R.id.soundMenu).setVisibility(LinearLayout.GONE);
				findViewById(R.id.cancelBar).setVisibility(LinearLayout.GONE);
				findViewById(R.id.back).setVisibility(ImageButton.VISIBLE);
				findViewById(R.id.tracks).setVisibility(LinearLayout.VISIBLE);
				findViewById(R.id.menuBar).setVisibility(LinearLayout.VISIBLE);
				findViewById(R.id.play).setFocusable(true);
				findViewById(R.id.play).setFocusableInTouchMode(true);
				findViewById(R.id.play).requestFocus();
			}
		});
        
        // Start the Joystick Mappers
        songMapper = new SongEditorMapper((LinearLayout)findViewById(R.id.tracks), (ImageButton)findViewById(R.id.play), (ImageButton)findViewById(R.id.stop), (ImageButton)findViewById(R.id.saveButton), (ImageButton)findViewById(R.id.back));
        reset = true;
        previousEvent = 0;
        instrumentMenu = false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beats_editor, menu);
		return true;
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
    		if(instrumentMenu)
    		{
    			View v, w;
    			w = getCurrentFocus();
    			if(x >= y)
	    		{
	    			reset = false;
	    			if(xPos)
	    			{
	    				v = instrumentMap.getNextFocus(w, MovementDirection.RIGHT);
	    			}
	    			else
	    			{
	    				v = instrumentMap.getNextFocus(w, MovementDirection.LEFT);
	    			}
	    		}
	    		else
	    		{
	    			reset = false;
	    			if(yPos)
	    			{
	    				v = instrumentMap.getNextFocus(w, MovementDirection.DOWN);
	    			}
	    			else
	    			{
	    				v = instrumentMap.getNextFocus(w, MovementDirection.UP);
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
    		else
    		{
	    		View v,w;
	    		w = getCurrentFocus();
	    		if(x >= y)
	    		{
	    			reset = false;
	    			if(xPos)
	    			{
	    				v = songMapper.getNextFocus(w, MovementDirection.RIGHT);
	    			}
	    			else
	    			{
	    				v = songMapper.getNextFocus(w, MovementDirection.LEFT);
	    			}
	    		}
	    		else
	    		{
	    			reset = false;
	    			if(yPos)
	    			{
	    				v = songMapper.getNextFocus(w, MovementDirection.DOWN);
	    			}
	    			else
	    			{
	    				v = songMapper.getNextFocus(w, MovementDirection.UP);
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
    	}

		return true;
    }
}
