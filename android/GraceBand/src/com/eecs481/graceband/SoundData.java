package com.eecs481.graceband;

public class SoundData {
	public int soundId;
	public int streamId;
	public boolean loaded;
	public boolean muted;
	
	public SoundData() {
		loaded = false;
		muted = false;
	}
}
