package com.eecs481.graceband;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Instruments {
	
	ArrayList<Button> buttonList;
	Context context;
	LinearLayout layout;
	int curLoc = 0;
	Button larrow;
	Button rarrow;
	
	Instruments(Context _context){
		buttonList = new ArrayList<Button>();
		context = _context;
		larrow = new Button(context);
		rarrow = new Button(context);
		larrow.setText("L");
		larrow.setTextColor(Color.BLACK);
		rarrow.setText("R");
		rarrow.setTextColor(Color.BLACK);
		larrow.setOnClickListener(leftClick);
		rarrow.setOnClickListener(leftClick);
	}
	
	// ADD SOUND FILE STUFF HERE?
	void createButton(String name){
		Button tempButton = new Button(context);
		tempButton.setText(name);
		tempButton.setTextColor(Color.BLACK);
		buttonList.add(tempButton);
	}
	
	void createScreen(){
		layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	    layout.setLayoutParams(layoutParams);
	    curLoc = 0;
	    layout.addView(larrow);
	    for(int i = curLoc; i < curLoc+3; i++){
	    	Button button = buttonList.get(i);
	    	button.setOnClickListener(centerClick);
	    	layout.addView(button);	    	
	    }
	    layout.addView(rarrow);
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
	    	Button button = buttonList.get(i);
	    	button.setOnClickListener(centerClick);
	    	layout.addView(button);	    	
	    }
	    layout.addView(rarrow);
	}
	
	void right(){
		layout.removeAllViewsInLayout();
		curLoc += 1;
		if(curLoc == buttonList.size()) curLoc = 0;
		int counter = 0;
		int curLocTemp = curLoc;
	    for(int i = curLoc; i < curLocTemp+3; i++, counter++){
	    	if(i == buttonList.size()) {
	    		i = 0;
	    		curLocTemp = -counter;
	    	}
	    	Button button = buttonList.get(i);
	    	button.setOnClickListener(centerClick);
	    	layout.addView(button);	    	
	    }
	}
	
	void center(){
		
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
    
    final OnClickListener centerClick = new OnClickListener() {
        public void onClick(final View v) {
        	center();
        }
    };
}
