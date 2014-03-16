package com.example.testjoystick;

import android.view.View;
import android.view.View.OnFocusChangeListener;

public class buttonFocusListener implements OnFocusChangeListener{

	@Override
	public void onFocusChange(View view, boolean state) {
		if(state)
			view.requestFocus();
	}
}
