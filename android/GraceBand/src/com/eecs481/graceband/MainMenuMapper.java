package com.eecs481.graceband;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainMenuMapper {

	private ImageButton start, quit, load;
	
	
	public MainMenuMapper(ImageButton st, ImageButton qu, ImageButton lo)
	{
		start =st;
		quit = qu;
		load = lo;
	}
	
	
	private View Goto_Down (View present)
	{
		if(present.getId() == start.getId())
			return load;
		else if(present.getId() == load.getId()) 
			return quit;
		return present;
	}
		
	
	private View Goto_Up ( View present)
	{	
		if(present.getId() == quit.getId())
			return load;
		else if(present.getId() == load.getId())
			return start;
		return present;
	}
	
	public View getNextFocus (View Current , MovementDirection dir)
	{
		View next;
		switch (dir)
		{
		case UP:
			next= Goto_Up(Current);
			break;
		case DOWN:
			next =Goto_Down(Current);
			break;
		default:
			next = Current;
			break;
		}
		return next; 
	}
}
