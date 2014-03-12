package com.eecs481.graceband;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Track {
	private int resid;
	private String name;
	public int get_resid(){return resid;}
	public String get_name(){return name;}
	public Track(int resid_, String name_){
		resid = resid_;
		name = name_;
	}
}

