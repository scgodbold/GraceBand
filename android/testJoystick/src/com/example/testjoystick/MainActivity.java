package com.example.testjoystick;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static double ZERO_TOLERANCE = .9;
	private static long TIME_TOLERANCE = 20;
	private boolean reset;
	private long previousEvent;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();
        
        reset = true;
        previousEvent = 0;
        findViewById(R.id.button1).setFocusableInTouchMode(true);
        findViewById(R.id.button1).setFocusable(true);
        findViewById(R.id.button2).setFocusableInTouchMode(false);
        findViewById(R.id.button2).setFocusable(false);
        findViewById(R.id.button3).setFocusableInTouchMode(false);
        findViewById(R.id.button3).setFocusable(false);
        findViewById(R.id.button4).setFocusableInTouchMode(false);
        findViewById(R.id.button4).setFocusable(false);
        findViewById(R.id.button1).requestFocus();
    }

    public boolean onGenericMotionEvent(MotionEvent event)
    {
    	float x = event.getX();
    	float y = event.getY();
    	TextView text = (TextView) findViewById(R.id.textView1);
    	TextView time = (TextView) findViewById(R.id.textView2);
    	
    	long diff = event.getEventTime() - previousEvent;
    	previousEvent = event.getEventTime();
    	time.setText(Long.toString(diff));
    	
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
    				text.setText("Right");
    				v = findViewById(R.id.button2);
    				v.requestFocus();
    				
    			}
    			else
    			{
    				text.setText("Left");
    				v = findViewById(R.id.button1);
    				v.requestFocus();
    				
    			}
    		}
    		else
    		{
    			reset = false;
    			if(yPos)
    			{
    				text.setText("Down");
    				v = findViewById(R.id.button3);
    				v.requestFocus();
    				
    			}
    			else
    			{
    				text.setText("Up");
    				v = findViewById(R.id.button1);
    				v.requestFocus();
    				
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
