package com.eecs481.graceband;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button buttonOne = (Button) findViewById(R.id.button4);
        final Button buttonTwo = (Button) findViewById(R.id.button2);
        final Button buttonThree = (Button) findViewById(R.id.button7);
        final Button buttonFour = (Button) findViewById(R.id.button3);
        final Button buttonFive = (Button) findViewById(R.id.button1);
        final Button buttonSix = (Button) findViewById(R.id.button6);
        final Button buttonSeven = (Button) findViewById(R.id.button9);
        final Button buttonEight = (Button) findViewById(R.id.button5);
        final Button buttonNine = (Button) findViewById(R.id.button8);
        
        buttonOne.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("1");
			}
		});
        buttonTwo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("2");
			}
		});
        buttonThree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("3");
			}
		});
        buttonFour.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("4");
			}
		});
        buttonFive.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("5");
			}
		});
        buttonSix.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("6");
			}
		});
        buttonSeven.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("7");
			}
		});
        buttonEight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("8");
			}
		});
        buttonNine.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView text = (TextView) findViewById(R.id.textView1);
				text.setText("9");
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
