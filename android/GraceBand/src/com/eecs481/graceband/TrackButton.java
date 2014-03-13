package com.eecs481.graceband;

import android.content.Context;
import android.widget.ImageButton;

public class TrackButton extends ImageButton {

	private Track buttonTrack;
	
	public TrackButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void setTrack(Track track) {
		buttonTrack = track;
	}
	
	public Track getTrack() {
		return buttonTrack;
	}
}
