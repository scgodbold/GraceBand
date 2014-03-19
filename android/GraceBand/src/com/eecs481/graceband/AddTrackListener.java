package com.eecs481.graceband;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageButton;
import android.widget.LinearLayout;

public class AddTrackListener implements OnClickListener {
	TrackHandle tracks;
	LinearLayout trackView;
	Activity activity;
	
	AddTrackListener(TrackHandle _t, View _view, Activity _activity)
	{
		tracks = _t;
		trackView = (LinearLayout) _view;
		activity = _activity;
	}
	
	@Override
	public void onClick(View v) {
		TrackButton button = (TrackButton) v;
		System.out.println("clicked button " + button.getTag().toString());
		int i = TrackList.get_instance().addTrack(activity.getApplicationContext(), button.getTrack());
		tracks.addTrack(button.getTrack(), i);
		BeatsEditor.instrumentMenu = false;
		activity.findViewById(R.id.soundMenu).setVisibility(LinearLayout.GONE);
		activity.findViewById(R.id.cancelBar).setVisibility(LinearLayout.GONE);
		activity.findViewById(R.id.back).setVisibility(ImageButton.VISIBLE);
		activity.findViewById(R.id.tracks).setVisibility(LinearLayout.VISIBLE);
		activity.findViewById(R.id.menuBar).setVisibility(LinearLayout.VISIBLE);
		activity.findViewById(R.id.play).setFocusable(true);
		activity.findViewById(R.id.play).setFocusableInTouchMode(true);
		activity.findViewById(R.id.play).requestFocus();
	}

}
