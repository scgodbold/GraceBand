package com.eecs481.graceband;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Track extends SoundPool {
	private int soundId;
	private int streamId;
	private float volume;

	//track attributes - add other things like image here
	private String name;

	public Track(){
		super(10, AudioManager.STREAM_MUSIC, 0);
	}

	public Track(String path) {
		super(10, AudioManager.STREAM_MUSIC, 0);
		soundId = this.load(path, 1);
	}
	
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
		streamId = this.play(soundId, 0.0f, 0.0f, 0, -1, 1f);
		streamId = this.play(soundId, 0.0f, 0.0f, 0, -1, 1f);
		streamId = this.play(soundId, volume, volume, 0, -1, 1f);
	}

	public void stop() {
		this.stop(streamId);
	}

	public void pause(){
		this.pause(streamId);
	}

	public int getSoundId(){
		return soundId;
	}

	public String getName(){
		return name;
	}

}