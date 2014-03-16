package com.eecs481.graceband;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	private MainMenuMapper menuMap;
	private static double ZERO_TOLERANCE = .97;
	private static long TIME_TOLERANCE = 20;
	private boolean reset;
	private long previousEvent;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        final ImageButton startButton = (ImageButton) findViewById(R.id.start);
        final ImageButton quitButton = (ImageButton) findViewById(R.id.quit);
        
        menuMap = new MainMenuMapper(startButton, quitButton);
        reset = true;
        previousEvent = 0;
        startButton.setFocusable(true);
        startButton.setFocusableInTouchMode(true);
        startButton.requestFocus();
        
        startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), BeatsEditor.class);
				startActivity(intent);
			}
		});
        
        quitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
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

		return false;
    }
    
}
