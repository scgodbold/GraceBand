package com.eecs481.graceband;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
//import android.widget.Button;
import android.widget.ImageView;
//import android.widget.TextView;

public class MainActivity extends Activity {

	private MainMenuMapper menuMap;
	private static double ZERO_TOLERANCE = .40;
	private static long TIME_TOLERANCE = 100;
	private boolean reset;
	private boolean lockTouch;
	private long previousEvent;
	
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        final ImageView startButton = (ImageView) findViewById(R.id.start);
        final ImageView loadButton = (ImageView) findViewById(R.id.load);
        final ImageView quitButton = (ImageView) findViewById(R.id.quit);
        
        menuMap = new MainMenuMapper(startButton, quitButton, loadButton);
        reset = true;
        lockTouch = false;
        previousEvent = 0;
        startButton.setFocusable(true);
        startButton.setFocusableInTouchMode(true);
        startButton.requestFocus();        
    }
    
    private void quitApp()
    {
    	finish();
    }
    
    private void loadMenu()
    {
    	Intent intent = new Intent(getBaseContext(), LoadMenu.class);
		startActivity(intent);
    }
    
    private void playMenu()
    {
    	Intent intent = new Intent(getBaseContext(), BeatsEditor.class);
		startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    		View v,w;
    		w = getCurrentFocus();
    		if(x >= y)
    		{
    			reset = false;
    			if(xPos)
    			{
    				v = menuMap.getNextFocus(w, MovementDirection.RIGHT);
    			}
    			else
    			{
    				v = menuMap.getNextFocus(w, MovementDirection.LEFT);
    			}
    		}
    		else
    		{
    			reset = false;
    			if(yPos)
    			{
    				v = menuMap.getNextFocus(w, MovementDirection.DOWN);
    			}
    			else
    			{
    				v = menuMap.getNextFocus(w, MovementDirection.UP);
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
	    		View current = getCurrentFocus();
	    		
	    		if(current.getId() == findViewById(R.id.start).getId())
	    		{
	    			playMenu();
	    		}
	    		if(current.getId() == findViewById(R.id.load).getId())
	    		{
	    			loadMenu();
	    		}
	    		if(current.getId() == findViewById(R.id.quit).getId())
	    		{
	    			quitApp();
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
