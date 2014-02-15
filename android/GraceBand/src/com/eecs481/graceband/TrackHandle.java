package com.eecs481.graceband;

import java.util.ArrayList;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TrackHandle {
	private ArrayList<LinearLayout> tracks; // Change data type to work with music thing
	private Context context;
	
	public TrackHandle(Context _context)
	{
		tracks = new ArrayList<LinearLayout>();
		context = _context;
	}
	
	public LinearLayout addTrack(String val)
	{
		LinearLayout l = new LinearLayout(context);
		TextView icon = new TextView(context);
		icon.setText(val);
		TextView trackImg = new TextView(context);
		trackImg.setText("  //  This works");
		l.addView(icon);
		l.addView(trackImg);
		tracks.add(l);
		return l;
	}
}
