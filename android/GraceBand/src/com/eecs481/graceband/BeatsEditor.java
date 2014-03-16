package com.eecs481.graceband;

//import android.media.AudioManager;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BeatsEditor extends Activity {

	TrackHandle t;	
	View trackView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beats_editor);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        final ImageButton playButton = (ImageButton) findViewById(R.id.play);
        final ImageButton stopButton = (ImageButton) findViewById(R.id.stop);
        final ImageButton saveButton = (ImageButton) findViewById(R.id.saveButton);
        final Button cancelButton = (Button) findViewById(R.id.cancel);
        final ImageButton backButton = (ImageButton) findViewById(R.id.back);
    
        t = new TrackHandle(getApplicationContext(), this);
        trackView = findViewById(R.id.tracks);
        /*
         Add a load button to this screen instead of main screen.
         Then, after loading into TrackList, build buttons with TrackHandle.addTrack() here
         */
        
        backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TrackList.get_instance().clearBeats();
				TrackList.get_instance().stopAll();
				finish();
			}
		});
        playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TrackList.get_instance().playAll();
			}
		});
        stopButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TrackList.get_instance().stopAll();
			}
		});
        
        saveButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		String saved_filename = "";
        		try {
					saved_filename = TrackList.get_instance().save(getBaseContext());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		Toast toast = Toast.makeText(getApplicationContext(), "Song saved as \""+saved_filename+"\"", Toast.LENGTH_LONG);
        		toast.show();
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
