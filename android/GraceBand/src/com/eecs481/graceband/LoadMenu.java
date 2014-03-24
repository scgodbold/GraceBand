package com.eecs481.graceband;

import java.io.IOException;
import java.util.ArrayList;

import com.eecs481.graceband.AllTracks.TrackNotFoundException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.view.View;
import android.support.v4.app.NavUtils;

public class LoadMenu extends Activity {

	private ListView lv;
	private LoadMenuAdapter adapter;
	private ArrayList<String> songArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActionBar().hide();
		// Show the Up button in the action bar.
		songArray = TrackList.get_instance().getFileList(getBaseContext());
		songArray.add("New Song");
		adapter = new LoadMenuAdapter(this, songArray);
		lv = (ListView) findViewById(R.id.list);
		lv.setAdapter(adapter);
	    lv.setOnItemClickListener(new OnItemClickListener()
	    {
	    	@Override
	        public void onItemClick(AdapterView<?> arg0, View arg1 ,int position, long arg3)
	        { 
				try {
					TrackList.get_instance().loadFile(getBaseContext(), songArray.get(position));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TrackNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		Intent intent = new Intent(getBaseContext(), BeatsEditor.class);
				startActivity(intent);
	        }
	    });
		
		setupActionBar();
	}
	
	public boolean onGenericMotionEvent(MotionEvent event)
    {
    	float x = event.getX();
    	float y = event.getY();

		return true;
    }

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.load_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
