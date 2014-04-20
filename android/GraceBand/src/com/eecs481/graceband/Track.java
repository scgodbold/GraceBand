package com.eecs481.graceband;

//import android.content.Context;
//import android.media.AudioManager;
//import android.media.SoundPool;

public class Track {
	private int resid;
	private String name;
	private int instrumentDrawable, beatMenuDrawable;
	private Category category;
	public int get_resid(){return resid;}
	public String get_name(){return name;}
	public int getInstrumentDrawable(){return instrumentDrawable;}
	public int getBeatMenuDrawable(){return beatMenuDrawable;}
	public Category getCategory(){return category;}
	public Track(int resid_, String name_, int _instrumentDrawable, int _beatMenuDrawable, Category _category){
		resid = resid_;
		name = name_;
		instrumentDrawable = _instrumentDrawable;
		beatMenuDrawable = _beatMenuDrawable;
		category = _category;
	}
}

