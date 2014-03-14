package com.eecs481.graceband;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class SongEditorMapper {
	private LinearLayout trackList;
	private ImageButton play, stop, save, back;

	public SongEditorMapper(LinearLayout _trackList, ImageButton _play, ImageButton _stop, ImageButton _save, ImageButton _back)
	{
		trackList = _trackList;
		play = _play;
		stop = _stop;
		save = _save;
		back = _back;
	}
	
	public View getNextFocus(View current, MovementDirection direction)
	{
		View next;
		switch (direction)
		{
			case UP:
				next = getNextUp(current);
				break;
			case DOWN:
				next = getNextDown(current);
				break;
			case LEFT:
				next = getNextLeft(current);
				break;
			case RIGHT:
				next = getNextRight(current);
				break;
			default:
				next = current;
				break;
		}
		return next;
	}
	
	private View getNextUp(View current)
	{
		if(current.getId() == play.getId() || current.getId() == stop.getId() || current.getId() == save.getId())
			return current;
		else if(current.getId() == back.getId())
			return ((LinearLayout)trackList.getChildAt(trackList.getChildCount()-1)).getChildAt(0);
		return play;
	}
	
	private View getNextDown(View current)
	{
		if(current.getId() == back.getId())
			return back;
		if(current.getId() == play.getId() || current.getId() == stop.getId() || current.getId() == save.getId())
			return ((LinearLayout)trackList.getChildAt(trackList.getChildCount()-1)).getChildAt(0);
		// Uncomment back and delete this line V when you change the back button to behave appropriately
		return ((LinearLayout)trackList.getChildAt(trackList.getChildCount()-1)).getChildAt(0);
		//return back;
	}
	
	private View getNextLeft(View current)
	{
		if(current.getId() == play.getId() && current.getId() == stop.getId())
			return play;
		else if(current.getId() == save.getId())
			return stop;
		else
		{
			int match = -1;
			for(int i=0; i<trackList.getChildCount(); i++)
			{
				if(current.getId() == ((View)((LinearLayout)trackList.getChildAt(i)).getChildAt(0)).getId())
				{
					match = i;
					break;
				}
			}
			if(match == 0)
				return current;
			else if(match > 0)
			{
				match--;
				return ((LinearLayout)trackList.getChildAt(match)).getChildAt(0);
			}
		}
		return current;
	}
	
	private View getNextRight(View current)
	{
		if(current.getId() == save.getId() || current.getId() == stop.getId())
			return save;
		else if(current.getId() == play.getId())
			return stop;
		else
		{
			int match = -1;
			for(int i=0; i<trackList.getChildCount(); i++)
			{
				if(current.getId() == ((View)((LinearLayout)trackList.getChildAt(i)).getChildAt(0)).getId())
				{
					match = i;
					break;
				}
			}
			if(match == trackList.getChildCount()-1)
				return current;
			else if(match > 0)
			{
				match++;
				return ((LinearLayout)trackList.getChildAt(match)).getChildAt(0);
			}
		}
		return current;
	}
}