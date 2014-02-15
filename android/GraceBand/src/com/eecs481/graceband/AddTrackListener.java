package com.eecs481.graceband;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
		// TODO Auto-generated method stub
		Button button = (Button) v;
		System.out.println("clicked button " + button.getText().toString());
		LinearLayout newLayout = tracks.addTrack(button.getText().toString());
		trackView.addView(newLayout);
		activity.findViewById(R.id.soundMenu).setVisibility(LinearLayout.GONE);
		activity.findViewById(R.id.cancelBar).setVisibility(LinearLayout.GONE);
		activity.findViewById(R.id.beatMenuBar).setVisibility(LinearLayout.VISIBLE);
		activity.findViewById(R.id.tracks).setVisibility(LinearLayout.VISIBLE);
		activity.findViewById(R.id.menuBar).setVisibility(LinearLayout.VISIBLE);
	}

}
