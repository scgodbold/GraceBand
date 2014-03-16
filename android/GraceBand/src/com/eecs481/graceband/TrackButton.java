package com.eecs481.graceband;

import android.content.Context;
import android.widget.ImageButton;

public class TrackButton extends ImageButton {

	private Track buttonTrack;
	
	public TrackButton(Context context) {
		super(context);
		setNextFocusDownId(this.getId());
		setNextFocusLeftId(this.getId());
		setNextFocusRightId(this.getId());
		setNextFocusUpId(this.getId());
		setFocusable(false);
		setFocusableInTouchMode(false);
		// TODO Auto-generated constructor stub
	}
	
	public void setTrack(Track track) {
		buttonTrack = track;
	}
	
	public Track getTrack() {
		return buttonTrack;
	}
}
