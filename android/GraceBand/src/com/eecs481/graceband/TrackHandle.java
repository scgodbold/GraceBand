package com.eecs481.graceband;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN) public class TrackHandle{
	private ArrayList<LinearLayout> tracks; // Change data type to work with music thing
	private Context context;
	private Activity activity;
	private LinearLayout list;
	static Integer ids = 0;
	private LinearLayout addTrackButtonBar;
	private ImageButton addTrackButton;
	Instruments instList;
	
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
				createSoundMenu();
				
			}
		});
		
		
		addTrackButtonBar.addView(addTrackButton);
		//l.addView(icon);
		//l.addView(trackImg);
		list.addView(addTrackButtonBar);
	}
	
	public void addTrack(String val, int id)
	{
		if(tracks.size() >= maxTracks)return;
		list.removeView(addTrackButtonBar);
		LinearLayout l = new LinearLayout(context);
		l.setId(ids++);
		
		ImageButton remove = new ImageButton(context);
		remove.setPadding(40, 40, 0, 40);
		remove.setBackgroundColor(Color.TRANSPARENT);
		remove.setId(id);
		if(val.contentEquals("Piano")){
			remove.setImageResource(R.drawable.selectorpiano);
		}
		else if(val.contentEquals("Snare Drum")){
			remove.setImageResource(R.drawable.selectorsnare);
		}
		else if(val.contentEquals("Bass Drum")){
			remove.setImageResource(R.drawable.selectorbass);
		}
		else if(val.contentEquals("Vocals")){
			remove.setImageResource(R.drawable.selectorvocals);
		}
		else{
			remove.setImageResource(R.drawable.selectorbeatbox);
		}
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
	
	public int getNumTracks()
	{
		return tracks.size();
	}
	
	void createSoundMenu(){
		instList = new Instruments(activity, this, activity.findViewById(R.id.tracks));
        instButtons();
        instList.createScreen();
        activity.findViewById(R.id.tracks).setVisibility(LinearLayout.GONE);
        activity.findViewById(R.id.menuBar).setVisibility(LinearLayout.GONE);
        activity.findViewById(R.id.cancelBar).setVisibility(LinearLayout.VISIBLE);
        activity.findViewById(R.id.soundMenu).setVisibility(LinearLayout.VISIBLE);
	}
	
	void instButtons(){
		AllTracks all_tracks = new AllTracks();
		ArrayList<Track> temp = all_tracks.tracks;
		instList.createButton("Snare Drum",temp.get(0).get_resid());
		instList.createButton("Bass Drum",temp.get(1).get_resid());
		instList.createButton("Piano",2);
		instList.createButton("BeatBox",3);
		instList.createButton("Vocals",4);
	}
}
