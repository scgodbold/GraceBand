package com.eecs481.graceband;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class AddTrackListener implements OnClickListener {
	TrackHandle tracks;
	LinearLayout trackView;
	Activity activity;
	Instruments instruments;
	
	AddTrackListener(TrackHandle _t, View _view, Activity _activity, Instruments _instruments)
	{
		tracks = _t;
		trackView = (LinearLayout) _view;
		activity = _activity;
		instruments = _instruments;
	}
	
	@Override
	public void onClick(View v) {
		ImageButton button = (ImageButton) v;
		System.out.println("clicked button " + button.getTag().toString());
		int i = TrackList.get_instance().addTrack(activity.getApplicationContext(), button.getId());
		tracks.addTrack(button.getTag().toString(), i);
		activity.findViewById(R.id.soundMenu).setVisibility(LinearLayout.GONE);
		activity.findViewById(R.id.cancelBar).setVisibility(LinearLayout.GONE);
		activity.findViewById(R.id.tracks).setVisibility(LinearLayout.VISIBLE);
		activity.findViewById(R.id.menuBar).setVisibility(LinearLayout.VISIBLE);
	}

}
