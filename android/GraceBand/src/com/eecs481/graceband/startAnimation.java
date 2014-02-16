package com.eecs481.graceband;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class startAnimation extends View {

	private Paint paint;
	int counter;
	
	public startAnimation(Context context) {
		super(context);
		paint = new Paint();
        paint.setTextSize(12);
        paint.setColor(0xFF668800);
        paint.setStyle(Paint.Style.FILL);
	}
	public startAnimation(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    paint = new Paint();
        paint.setTextSize(12);
        paint.setColor(0xFF668800);
        paint.setStyle(Paint.Style.FILL);
	}

    @Override
    protected void onDraw(Canvas canvas) {
    	counter++;
        canvas.drawText("TEEEST " + counter/10, 250, 250, paint);
        animator.run();
    }
    
    private Runnable animator = new Runnable() {
        long old = 0;
        @Override
        public void run() {
            boolean scheduleNewFrame = false;
            long now = System.currentTimeMillis();
            if(now - old > 100){
            	old = now;
            	scheduleNewFrame = true;
            }
            if (scheduleNewFrame) {
                postDelayed(this, 15);
            }
            invalidate();
        }
    };
}
