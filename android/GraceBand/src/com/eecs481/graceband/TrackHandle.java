package com.eecs481.graceband;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN) public class TrackHandle{
	private ArrayList<LinearLayout> tracks; // Change data type to work with music thing
	private Context context;
	private Activity activity;
	
	static Integer ids = 0;
	
	public TrackHandle(Context _context, Activity _activity)
	{
		tracks = new ArrayList<LinearLayout>();
		context = _context;
		activity = _activity;
	}
	
	public LinearLayout addTrack(String val)
	{
		LinearLayout l = new LinearLayout(context);
		l.setId(ids++);
		TextView icon = new TextView(context);
		icon.setText(val);
		icon.setTextSize(28);
		TextView trackImg = new TextView(context);
		trackImg.setText("  //  This works");
		
		int[] attrs = new int[] { android.R.attr.selectableItemBackground /* index 0 */};
	    TypedArray ta = context.getTheme().obtainStyledAttributes(attrs);
	    Drawable drawableFromTheme = ta.getDrawable(0 /* index */);
	    ta.recycle();
		Button remove = new Button(context);
		remove.setText("X");
		remove.setTextColor(Color.BLACK);
		remove.setBackground(drawableFromTheme);
		remove.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinearLayout layout = (LinearLayout) activity.findViewById(R.id.tracks);
				LinearLayout removed = (LinearLayout) v.getParent();
				layout.removeView(removed);
				tracks.remove(removed);
			}
		});
		
		
		l.addView(remove);
		l.addView(icon);
		l.addView(trackImg);
		tracks.add(l);
		return l;
	}
	
	public int getNumTracks()
	{
		return tracks.size();
	}
}
