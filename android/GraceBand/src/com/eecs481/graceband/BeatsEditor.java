package com.eecs481.graceband;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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
        
        final Button playButton = (Button) findViewById(R.id.play);
        final Button pauseButton = (Button) findViewById(R.id.pause);
        final Button beatMenuButton = (Button) findViewById(R.id.beatMenu);
        final Button saveButton = (Button) findViewById(R.id.saveButton);
        final Button cancelButton = (Button) findViewById(R.id.cancel);
        
        // Tracks Handler
        t = new TrackHandle(this.getApplicationContext(), this);
        trackView = findViewById(R.id.tracks);
		//testButton.setOnClickListener( new AddTrackListener(t, trackView, this));
        
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
				System.out.println("clicked beats menu");
				createSoundMenu();
			}
		});
        cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				findViewById(R.id.soundMenu).setVisibility(LinearLayout.GONE);
				findViewById(R.id.cancelBar).setVisibility(LinearLayout.GONE);
				findViewById(R.id.beatMenuBar).setVisibility(LinearLayout.VISIBLE);
				findViewById(R.id.tracks).setVisibility(LinearLayout.VISIBLE);
				findViewById(R.id.menuBar).setVisibility(LinearLayout.VISIBLE);
			}
		});
	}

	void createSoundMenu(){
		instList = new Instruments(this, t, trackView);
        instButtons();
        instList.createScreen();
        findViewById(R.id.beatMenuBar).setVisibility(LinearLayout.GONE);
        findViewById(R.id.tracks).setVisibility(LinearLayout.GONE);
        findViewById(R.id.menuBar).setVisibility(LinearLayout.GONE);
        findViewById(R.id.cancelBar).setVisibility(LinearLayout.VISIBLE);
        findViewById(R.id.soundMenu).setVisibility(LinearLayout.VISIBLE);
	}
	
	void instButtons(){
		instList.createButton("Snare Drum");
		instList.createButton("Bass Drum ");
		instList.createButton("Piano     ");
		instList.createButton("BeatBox   ");
		instList.createButton("Vocals    ");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.beats_editor, menu);
		return true;
	}

}
