package com.eecs481.graceband;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class BeatsEditor extends Activity {

	TrackHandle t;	
	View trackView;
	Instruments instList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beats_editor);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        final ImageButton playButton = (ImageButton) findViewById(R.id.play);
        final ImageButton pauseButton = (ImageButton) findViewById(R.id.pause);
        //final ImageButton beatMenuButton = (ImageButton) findViewById(R.id.beatMenu);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.saveButton);
        final Button cancelButton = (Button) findViewById(R.id.cancel);
        final ImageButton backButton = (ImageButton) findViewById(R.id.back);
        
        int[] testSoundGroup = new int[2];
        testSoundGroup[0] = R.raw.kick;
        testSoundGroup[1] = R.raw.ride_bell;

        final Track singleTrack = new Track(this, R.raw.kick);
        final Track singleTrack2 = new Track(this, R.raw.ride_bell);
        final Track multiTrack = new Track(this, testSoundGroup);
        
        
        // Tracks Handler
        t = new TrackHandle(this.getApplicationContext(), this);
        trackView = findViewById(R.id.tracks);
		//testButton.setOnClickListener( new AddTrackListener(t, trackView, this));
        
        backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				multiTrack.play();
			}
		});
        pauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
        
        saveButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		singleTrack2.play();
        	}
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				findViewById(R.id.soundMenu).setVisibility(LinearLayout.GONE);
				findViewById(R.id.cancelBar).setVisibility(LinearLayout.GONE);
				findViewById(R.id.tracks).setVisibility(LinearLayout.VISIBLE);
				findViewById(R.id.menuBar).setVisibility(LinearLayout.VISIBLE);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beats_editor, menu);
		return true;
	}

}
