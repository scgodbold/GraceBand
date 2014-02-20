package com.eecs481.graceband;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Track extends SoundPool {
	
	private SoundData[] soundIds;
	private float volume;
	private boolean isPlaying = false;
	
	public Track(Context context, int[] resId) {
		super(10, AudioManager.STREAM_MUSIC, 0);
		
		for (int i = 0; i < resId.length; i++) {
			soundIds[i] = new SoundData();
			soundIds[i].soundId = this.load(context, resId[i], 1);
		}
		
	}
	
	public Track(Context context, int resId) {
		super(10, AudioManager.STREAM_MUSIC, 0);
		soundIds[0] = new SoundData();
		soundIds[0].soundId = this.load(context, resId, 1);

	}
	
	public void play() {
		if (!isPlaying) {
			for (int i = 0; i < soundIds.length; i++) {
				soundIds[i].streamId = this.play(soundIds[i].soundId, volume, volume, 1, 0, 1f);
			}
			isPlaying = true;
		}
	}
	
	public void stop() {
		if (isPlaying) {
			for (int i = 0; i < soundIds.length; i++) {
				this.stop(soundIds[i].streamId);
			}
			isPlaying = false;
		}
	}
	
	
}
