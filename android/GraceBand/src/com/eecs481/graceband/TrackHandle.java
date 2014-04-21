package com.eecs481.graceband;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1) public class TrackHandle{
	private ArrayList<LinearLayout> tracks; // Change data type to work with music thing
	private Context context;
	private Activity activity;
	private LinearLayout list;
	static Integer ids = 0;
	private LinearLayout addTrackButtonBar;
	private ImageView addTrackButton;
	private InstrumentSelectionMapper map;
	private ArrayList<LinearLayout> categoryList;
	final int maxTracks = 5;
	
	public TrackHandle(Activity _activity, InstrumentSelectionMapper _map)
	{
		map = _map;
		tracks = new ArrayList<LinearLayout>();
		activity = _activity;
		context = activity.getApplicationContext();

		list = (LinearLayout) activity.findViewById(R.id.tracks);
		addTrackButtonBar = (LinearLayout) activity.findViewById(R.id.addTrackButtonBar);
		addTrackButton = (ImageView) activity.findViewById(R.id.addTrackButton);
		addTrackButtonBar.setVisibility(View.VISIBLE);
		addTrackButton.setFocusable(true);
		addTrackButton.setFocusableInTouchMode(true);
		addTrackButton.requestFocus();
		/*addTrackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("clicked beats menu");
				TrackList.get_instance().stopAll();
				BeatsEditor.instrumentMenu = true;
				viewSoundMenu();
				addTrackButton.setFocusable(false);
				addTrackButton.setFocusableInTouchMode(false);
				/*View y;
				y = map.getCurrent();
				y.setFocusable(true);
				y.setFocusableInTouchMode(true);
				y.requestFocus();
			}
		});*/
		
		createSoundMenu();
		for(int i = 0; i < TrackList.get_instance().Beats.size(); i++){
			addTrack(TrackList.get_instance().Beats.get(i).track, TrackList.get_instance().Beats.get(i).position);
		}
		
	}
	
	public boolean containsTrack(LinearLayout track)
	{
		return tracks.contains(track);
	}
	public int getNumTracks()
	{
		return tracks.size();
	}
	
	public void removeTrack(LinearLayout track)
	{
		tracks.remove(track);
	}
	
	public void addSpecialButton(LinearLayout _addTrackButton)
	{
		tracks.add(_addTrackButton);
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
		/*remove.setOnClickListener(new View.OnClickListener() {
			
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
				View play = activity.findViewById(R.id.play);
				play.setFocusable(true);
				play.setFocusableInTouchMode(true);
				play.requestFocus();
			}
		});*/
		remove.setBaselineAlignBottom(true);
		remove.setFocusable(false);
		remove.setFocusableInTouchMode(false);
		remove.setNextFocusDownId(remove.getId());
		remove.setNextFocusLeftId(remove.getId());
		remove.setNextFocusRightId(remove.getId());
		remove.setNextFocusUpId(remove.getId());
		l.setBaselineAligned(false);
		l.addView(remove);
		tracks.add(l);
		list.addView(l);
		if(tracks.size() < maxTracks) {
			list.addView(addTrackButtonBar);
		}
	}
	
	void viewSoundMenu() {
        activity.findViewById(R.id.tracks).setVisibility(LinearLayout.GONE);
        activity.findViewById(R.id.menuBar).setVisibility(LinearLayout.GONE);
        activity.findViewById(R.id.back).setVisibility(ImageView.GONE);
        activity.findViewById(R.id.cancelBar).setVisibility(LinearLayout.VISIBLE);
        activity.findViewById(R.id.soundMenu).setVisibility(LinearLayout.VISIBLE);
	}
	
	void createSoundMenu(){
		categoryList = new ArrayList<LinearLayout>();
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.soundMenu);
		
		ImageView larrow = new ImageView(context);
		larrow.setImageResource(R.drawable.ic_launcher);
		larrow.setPadding(20, 250, 20, 0);
		
		ImageView rarrow = new ImageView(context);
		rarrow.setImageResource(R.drawable.ic_launcher);
		rarrow.setPadding(20, 250, 20, 0);
		
		for(int i = 0; i < Category.values().length; i++) {
			categoryList.add(createButtonList(Category.values()[i]));
		}
		//map.setButtonList(buttonList);

		layout.addView(larrow);
		
		for(int i=0; i<4; i++){
	    	layout.addView(categoryList.get(i));	    	
	    }
		
		layout.addView(rarrow);
	}
	
	LinearLayout createButtonList(Category category) {
		ArrayList<TrackButton> buttonList = new ArrayList<TrackButton>();
		AllTracks all_tracks = new AllTracks();
		
		TextView text = new TextView(context);
		text.setTextColor(Color.BLACK);
		text.setGravity(Gravity.CENTER);
		text.setTextSize(30);
		text.setTypeface(Typeface.DEFAULT_BOLD);
		text.setPadding(65, 20, 0, 10);
		//text.setPadding(left, top, right, bottom);
		text.setText(category.toString());
		
		for(int i = 0; i < all_tracks.tracks.size(); i++) {
			Track temp = all_tracks.tracks.get(i);
			if(temp.getCategory().name().contentEquals(category.toString())){
				createButton(temp,buttonList);
			}
		}
		//map.setButtonList(buttonList);
		
    	LinearLayout layout = new LinearLayout(context);
    	layout.setOrientation(LinearLayout.VERTICAL);
    	layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    	layout.addView(text);
		for(int i=0; i<buttonList.size(); i++){
			TrackButton temp = buttonList.get(i);
			if(i > 1) {
				temp.setVisibility(TrackButton.GONE);
			}
	    	layout.addView(temp);	    	
	    }

		return layout;
	}
	
	void createButton(Track track, ArrayList<TrackButton> buttonList){
		System.out.println("creating button " + track.get_name());
		TrackButton tempButton = new TrackButton(context);
		//tempButton.setOnHoverListener(onHover);
		tempButton.setTrack(track);
		tempButton.setId(track.get_resid());
		tempButton.setPadding(65, 10, 0, 10);
		tempButton.setTag(track.get_name());

		tempButton.setImageResource(track.getInstrumentDrawable());
		tempButton.setFocusableInTouchMode(false);
        tempButton.setFocusable(false);
        
        tempButton.setNextFocusDownId(tempButton.getId());
        tempButton.setNextFocusLeftId(tempButton.getId());
        tempButton.setNextFocusRightId(tempButton.getId());
        tempButton.setNextFocusUpId(tempButton.getId());
        
    	tempButton.setOnClickListener(new AddTrackListener(this, activity.findViewById(R.id.tracks), activity));
    	
    	tempButton.setBackgroundColor(Color.TRANSPARENT);

    	buttonList.add(tempButton);
	}
}
