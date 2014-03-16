package com.eecs481.graceband;

import java.io.IOException;

import com.eecs481.graceband.AllTracks.TrackNotFoundException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
//import android.widget.Button;
import android.widget.ImageButton;
//import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
        
        final ImageButton startButton = (ImageButton) findViewById(R.id.start);
        final ImageButton loadButton = (ImageButton) findViewById(R.id.load);
        final ImageButton quitButton = (ImageButton) findViewById(R.id.quit);
        
        startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), BeatsEditor.class);
				startActivity(intent);
			}
		});
        
        
        loadButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					TrackList.get_instance().load(getBaseContext());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (TrackNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
        
        quitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//finish();//
					try {
						TrackList.get_instance().load(getBaseContext());
					} catch (IOException e) {
						e.printStackTrace();
					} catch (TrackNotFoundException e) {
						e.printStackTrace();
					}
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
