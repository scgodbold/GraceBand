package com.eecs481.graceband;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class startAnimation extends View {

	private Paint paint;
	int counter = 0;
	int counter2 = -300;
	int counter3 = -650;
	int counter4 = -950;
	int rand;
	int rand2;
	int rand3;
	int rand4;
	Random random;
	Bitmap a, b;
	
	public startAnimation(Context context) {
		super(context);
		paint = new Paint();
        paint.setTextSize(12);
        paint.setColor(0xFF668800);
        paint.setStyle(Paint.Style.FILL);
        a = BitmapFactory.decodeResource(getResources(), R.drawable.eighth);
    	b = BitmapFactory.decodeResource(getResources(), R.drawable.note);
    	random = new Random(); 
    	rand = random.nextInt(150);
    	rand2 = random.nextInt(150);
    	rand3 = random.nextInt(150);
    	rand4 = random.nextInt(150);
	}
	public startAnimation(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    paint = new Paint();
        paint.setTextSize(12);
        paint.setColor(0xFF668800);
        paint.setStyle(Paint.Style.FILL);
        a = BitmapFactory.decodeResource(getResources(), R.drawable.eighth);
    	b = BitmapFactory.decodeResource(getResources(), R.drawable.note);
    	random = new Random(); 
    	rand = random.nextInt(150);
    	rand2 = random.nextInt(150);
    	rand3 = random.nextInt(150);
    	rand4 = random.nextInt(150);
	}

    @Override
    protected void onDraw(Canvas canvas) {
    	counter++;
    	counter2++;
    	counter3++;
    	counter4++;
    	if(counter > 1425){
    		counter = 0;
    		rand = random.nextInt(150);
    	}
    	if(counter2 > 1425){
    		counter2 = 0;
    		rand2 = random.nextInt(120);
    	}
    	if(counter3 > 1425){
    		counter3 = 0;
    		rand3 = random.nextInt(150);
    	}
    	if(counter4 > 1425){ 
    		counter4 = 0;
    		rand4 = random.nextInt(120);
    	}
    	
    	float sinVar = (float)(rand*Math.sin((double)counter/(double)100.0)+150);
    	float sinVar2 = (float)(rand2*Math.sin((double)counter2/(double)100.0)+100);
    	float sinVar3 = (float)(rand3*Math.sin((double)-counter3/(double)100.0)+150);
    	float sinVar4 = (float)(rand4*Math.sin((double)-counter4/(double)100.0)+100);
    	
    	canvas.drawBitmap(b, (float)(counter-50), sinVar, paint);
    	canvas.drawBitmap(a, counter2-225, sinVar2, paint);
    	canvas.drawBitmap(b, (float)(counter3-50), sinVar3, paint);
    	canvas.drawBitmap(a, counter4-225, sinVar4, paint);
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
