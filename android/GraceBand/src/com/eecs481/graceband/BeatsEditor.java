package com.eecs481.graceband;

//import android.media.AudioManager;
import java.io.IOException;

import com.eecs481.graceband.AllTracks.TrackNotFoundException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BeatsEditor extends Activity {

	TrackHandle t;	
	View trackView;
	
	private static double ZERO_TOLERANCE = .80;
	private static long TIME_TOLERANCE = 30;
	private boolean reset;
	private boolean lockTouch;
	private long previousEvent;
	private SongEditorMapper songMapper;
	public InstrumentSelectionMapper instrumentMap;
	public AllTracks allTracks;
	static public boolean instrumentMenu;
	
	private LinearLayout addTrackButtonBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beats_editor);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        addTrackButtonBar = (LinearLayout) findViewById(R.id.addTrackButtonBar);
        
        Intent intent = getIntent();
        if(intent.hasExtra("saveFileName")){
        	String saveFileName = intent.getStringExtra("saveFileName");
        	try {
				TrackList.get_instance().loadFile(getBaseContext(), saveFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TrackNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
        final ImageView playButton = (ImageView) findViewById(R.id.play);
        final ImageView stopButton = (ImageView) findViewById(R.id.stop);
        final ImageView saveButton = (ImageView) findViewById(R.id.saveButton);
        final ImageView cancelButton = (ImageView) findViewById(R.id.cancelBar);
        final ImageView backButton = (ImageView) findViewById(R.id.back);
    
        allTracks = new AllTracks();
        instrumentMap = new InstrumentSelectionMapper(this, allTracks);
        
        t = new TrackHandle(this, instrumentMap);
        trackView = findViewById(R.id.tracks);
        /*
         Add a load button to this screen instead of main screen.
         Then, after loading into TrackList, build buttons with TrackHandle.addTrack() here
         */
        
        // Start the Joystick Mappers
        songMapper = new SongEditorMapper((LinearLayout)findViewById(R.id.tracks), (ImageView)findViewById(R.id.play), (ImageView)findViewById(R.id.stop), (ImageView)findViewById(R.id.saveButton), (ImageView)findViewById(R.id.back));
        reset = true;
        lockTouch = false;
        previousEvent = 0;
        instrumentMenu = false;
	}
	
	private void back(){
		TrackList.get_instance().stopAll();
		TrackList.get_instance().clearBeats();
		NavUtils.navigateUpFromSameTask(this);
	}
	
	private void addTrack()
	{
		System.out.println("clicked beats menu");
		TrackList.get_instance().stopAll();
		instrumentMap.reset();
		t.createSoundMenu();
		BeatsEditor.instrumentMenu = true;
		View y;
		y = instrumentMap.getCurrent();
		LinearLayout parent = (LinearLayout) y.getParent();
		parent.getChildAt(1).setVisibility(ImageView.VISIBLE);
		parent.getChildAt(parent.getChildCount()-1).setVisibility(ImageView.VISIBLE);
		y.setFocusable(true);
		y.setFocusableInTouchMode(true);
		y.requestFocus();
		findViewById(R.id.tracks).setVisibility(LinearLayout.GONE);
        findViewById(R.id.menuBar).setVisibility(LinearLayout.GONE);
        findViewById(R.id.back).setVisibility(ImageView.GONE);
        findViewById(R.id.cancelBar).setVisibility(LinearLayout.VISIBLE);
        findViewById(R.id.soundMenu).setVisibility(LinearLayout.VISIBLE);
	}
	
	private void save()
	{
		String saved_filename = "";
		try {
			saved_filename = TrackList.get_instance().save(getBaseContext());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast toast = Toast.makeText(getApplicationContext(), "Song saved as \""+saved_filename+"\"", Toast.LENGTH_LONG);
		toast.show();
	}
	
	private void stop()
	{
		TrackList.get_instance().stopAll();
	}
	
	private void play()
	{
		TrackList.get_instance().playAll();
	}
	
	private void removeTrack(View v)
	{
		LinearLayout layout = (LinearLayout) findViewById(R.id.tracks);
		LinearLayout removed = (LinearLayout) v.getParent();
		layout.removeView(removed);
		if(t.getNumTracks() == 5) {
			//t.addSpecialButton(addTrackButtonBar);
			layout.addView(addTrackButtonBar);
		}
		t.removeTrack(removed);
		TrackList.get_instance().removeTrack(v.getId());
		View play = findViewById(R.id.play);
		play.setFocusable(true);
		play.setFocusableInTouchMode(true);
		play.requestFocus();
	}
	
	private void cancel()
	{
		BeatsEditor.instrumentMenu = false;
		findViewById(R.id.soundMenu).setVisibility(LinearLayout.GONE);
		findViewById(R.id.cancelBar).setVisibility(LinearLayout.GONE);
		findViewById(R.id.back).setVisibility(ImageView.VISIBLE);
		findViewById(R.id.tracks).setVisibility(LinearLayout.VISIBLE);
		findViewById(R.id.menuBar).setVisibility(LinearLayout.VISIBLE);
		findViewById(R.id.play).setFocusable(true);
		findViewById(R.id.play).setFocusableInTouchMode(true);
		findViewById(R.id.play).requestFocus();
	}
	
	private void addTrackButton(View v)
	{
		TrackButton button = (TrackButton) v;
		System.out.println("clicked button " + button.getTag().toString());
		int i = TrackList.get_instance().addTrack(getApplicationContext(), button.getTrack());
		t.addTrack(button.getTrack(), i);
		instrumentMenu = false;
		findViewById(R.id.soundMenu).setVisibility(LinearLayout.GONE);
		findViewById(R.id.cancelBar).setVisibility(LinearLayout.GONE);
		findViewById(R.id.back).setVisibility(ImageButton.VISIBLE);
		findViewById(R.id.tracks).setVisibility(LinearLayout.VISIBLE);
		findViewById(R.id.menuBar).setVisibility(LinearLayout.VISIBLE);
		findViewById(R.id.play).setFocusable(true);
		findViewById(R.id.play).setFocusableInTouchMode(true);
		findViewById(R.id.play).requestFocus();
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
	
	public boolean onTouchEvent(MotionEvent event)
    {
    	
    	if(event.getAction() == MotionEvent.ACTION_DOWN)
    	{
    		if(!lockTouch)
    		{
	    		lockTouch = true;
	    		System.out.println("Down Event");
	    		View current = getCurrentFocus();
	    		if(!instrumentMenu)
	    		{
		    		if(current.getId() == findViewById(R.id.play).getId())
		    		{
		    			play();
		    		}
		    		else if(current.getId() == findViewById(R.id.stop).getId())
		    		{
		    			stop();
		    		}
		    		else if(current.getId() == findViewById(R.id.saveButton).getId())
		    		{
		    			save();
		    		}
		    		else if(current.getId() == findViewById(R.id.back).getId())
		    		{
		    			back();
		    		}
		    		else if(current.getId() == addTrackButtonBar.getChildAt(0).getId())
		    		{
		    			addTrack();
		    		}
		    		else
		    		{
		    			removeTrack(current);
		    		}
	    		}
	    		else
	    		{
	    			if(current.getId() == findViewById(R.id.cancelBar).getId())
	    			{
	    				cancel();
	    			}
	    			else
	    			{
	    				addTrackButton(current);
	    			}
	    		}
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
