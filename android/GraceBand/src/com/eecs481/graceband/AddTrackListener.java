package com.eecs481.graceband;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class AddTrackListener implements OnClickListener {
	TrackHandle tracks;
	LinearLayout trackView;
	
	AddTrackListener(TrackHandle _t, View _view)
	{
		tracks = _t;
		trackView = (LinearLayout) _view;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		LinearLayout newLayout = tracks.addTrack("Piano");
		trackView.addView(newLayout);
	}

}
