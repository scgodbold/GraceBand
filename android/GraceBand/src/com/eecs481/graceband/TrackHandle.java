package com.eecs481.graceband;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN) public class TrackHandle{
	private ArrayList<LinearLayout> tracks; // Change data type to work with music thing
	private Context context;
	private Activity activity;
	private LinearLayout list;
	static Integer ids = 0;
	private LinearLayout addTrackButtonBar;
	private ImageButton addTrackButton;
	private ArrayList<LinearLayout> buttonList;
	
	final int maxTracks = 5;
	
	public TrackHandle(Context _context, Activity _activity)
	{
		tracks = new ArrayList<LinearLayout>();
		context = _context;
		activity = _activity;

		list = (LinearLayout) activity.findViewById(R.id.tracks);
		addTrackButtonBar = new LinearLayout(context);
		addTrackButton = new ImageButton(context);
		addTrackButton.setPadding(40, 40, 0, 40);
		addTrackButton.setBackgroundColor(Color.TRANSPARENT);
		addTrackButton.setImageResource(R.drawable.add_track_selector);
		addTrackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("clicked beats menu");
				TrackList.get_instance().stopAll();
				createSoundMenu();
			}
		});
		
		
		addTrackButtonBar.addView(addTrackButton);
		//l.addView(icon);
		//l.addView(trackImg);
		list.addView(addTrackButtonBar);
		
		for(int i = 0; i < TrackList.get_instance().Beats.size(); i++){
			addTrack(TrackList.get_instance().Beats.get(i).track, TrackList.get_instance().Beats.get(i).position);
		}
		
	}
	
	public void addTrack(Track track, int position_id)
	{
		if(tracks.size() >= maxTracks)return;
		list.removeView(addTrackButtonBar);
		LinearLayout l = new LinearLayout(context);
		l.setId(ids++);
		
		TrackButton remove = new TrackButton(context);
		remove.setTrack(track);
		remove.setPadding(40, 40, 0, 40);
		remove.setBackgroundColor(Color.TRANSPARENT);
		remove.setId(position_id);
		remove.setImageResource(track.getBeatMenuDrawable());
		remove.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LinearLayout layout = (LinearLayout) activity.findViewById(R.id.tracks);
				LinearLayout removed = (LinearLayout) v.getParent();
				layout.removeView(removed);
				if(tracks.size() == maxTracks) {
					list.addView(addTrackButtonBar);
				}
				tracks.remove(removed);
				TrackList.get_instance().removeTrack(v.getId());
				
			}
		});
		remove.setBaselineAlignBottom(true);
		l.setBaselineAligned(false);
		l.addView(remove);
		tracks.add(l);
		list.addView(l);
		if(tracks.size() < maxTracks) {
			list.addView(addTrackButtonBar);
		}
	}
	
	void createSoundMenu(){
		buttonList = new ArrayList<LinearLayout>();
		AllTracks all_tracks = new AllTracks();
		for(int i = 0; i < all_tracks.tracks.size(); i++) {
			createButton(all_tracks.tracks.get(i));
		}
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.soundMenu);
		layout.removeAllViewsInLayout();
	    for(LinearLayout button : buttonList){
	    	layout.addView(button);	    	
	    }
        activity.findViewById(R.id.tracks).setVisibility(LinearLayout.GONE);
        activity.findViewById(R.id.menuBar).setVisibility(LinearLayout.GONE);
        activity.findViewById(R.id.cancelBar).setVisibility(LinearLayout.VISIBLE);
        activity.findViewById(R.id.soundMenu).setVisibility(LinearLayout.VISIBLE);
	}
	
	void createButton(Track track){
		System.out.println("creating button " + track.get_name());
		TrackButton tempButton = new TrackButton(context);
		//tempButton.setOnHoverListener(onHover);
		tempButton.setTrack(track);
		tempButton.setId(track.get_resid());
		tempButton.setPadding(46, 60, 0, 46);
		tempButton.setTag(track.get_name());
		TextView text = new TextView(context);
		text.setTextColor(Color.BLACK);
		text.setGravity(Gravity.CENTER_HORIZONTAL);
		text.setPadding(46, 0, 0, 0);
		text.setText(track.get_name());
		tempButton.setImageResource(track.getBeatMenuDrawable());
		
    	tempButton.setOnClickListener(new AddTrackListener(this, activity.findViewById(R.id.tracks), activity));
    	
    	tempButton.setBackgroundColor(Color.TRANSPARENT);
    	LinearLayout l = new LinearLayout(context);
    	l.setOrientation(LinearLayout.VERTICAL);
    	l.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	l.addView(tempButton);
    	l.addView(text);
    	buttonList.add(l);
	}
}
