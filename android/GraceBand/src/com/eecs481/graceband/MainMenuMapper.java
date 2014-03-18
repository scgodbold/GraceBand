package com.eecs481.graceband;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainMenuMapper {

	private ImageButton start, quit;
	
	
	public MainMenuMapper(ImageButton st, ImageButton qu)
	{
		start =st;
		quit = qu;
	}
	
	
	private View Goto_Down (View present)
	{
		if(present.getId() == start.getId())
			return quit;
		return present;
	}
		
	
	private View Goto_Up ( View present)
	{	
		if(present.getId() == quit.getId())
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
