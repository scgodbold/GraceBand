package com.eecs481.graceband;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Track extends SoundPool {
	private String name;
	private int soundId;
	private int streamId;
	private float volume;

	public Track(Context context, int resId) {
		super(10, AudioManager.STREAM_MUSIC, 0);
		getVolume(context);
		soundId = this.load(context, resId, 1);

	}
	
	private void getVolume(Context context) {
	    AudioManager audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
	    float actualVolume = (float) audioManager
	        .getStreamVolume(AudioManager.STREAM_MUSIC);
	    float maxVolume = (float) audioManager
	        .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	    volume = actualVolume / maxVolume;
	}
	
	public void play() {
		streamId = this.play(soundId, volume, volume, 1, -1, 1f);
	}
	
	public void stop() {
		this.stop(streamId);
	}
	
	public void pause(){
		//??
	}

	public String getName(){
		return name;
	}
	
}
