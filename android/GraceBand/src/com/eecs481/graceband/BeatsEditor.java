package com.eecs481.graceband;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class BeatsEditor extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beats_editor);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        final Button playButton = (Button) findViewById(R.id.play);
        final Button pauseButton = (Button) findViewById(R.id.pause);
        final Button beatMenuButton = (Button) findViewById(R.id.beatMenu);
        final Button testButton = (Button) findViewById(R.id.testButton);
        
        // Tracks Handler
        TrackHandle t = new TrackHandle(this.getApplicationContext());
		testButton.setOnClickListener( new AddTrackListener(t, findViewById(R.id.tracks)));
        
        playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
        pauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
        beatMenuButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), SoundsMenu.class);
				startActivity(intent);
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
