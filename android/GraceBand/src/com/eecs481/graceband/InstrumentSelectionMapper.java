package com.eecs481.graceband;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class InstrumentSelectionMapper {

	private Activity activity;
	private AllTracks allTracks;
	
	ArrayList<ArrayList<TrackButton>> mapList;
	ArrayList<Integer> categoryIndex;
	ArrayList<LinearLayout> categoryList;
	
	private int current;
	private int pos;
	private boolean cancel;
	
	ImageView larrow;
	ImageView rarrow;
	
	public InstrumentSelectionMapper(Activity _activity, AllTracks _allTracks) {
		activity = _activity;
		allTracks = _allTracks;
		current = 0;
		pos = 0;
        cancel = false;
		larrow = new ImageView(activity.getApplicationContext());
		larrow.setImageResource(R.drawable.left_arrow);
		larrow.setPadding(5, 250, 5, 0);
		
		rarrow = new ImageView(activity.getApplicationContext());
		rarrow.setImageResource(R.drawable.right_arrow);
		rarrow.setPadding(5, 250, 5, 0);
	}
	
	public void reset(){
		current = 0;
		pos = 0;
		cancel = false;
	}
	
	public void setMapList(ArrayList<ArrayList<TrackButton>> _mapList){
		mapList = _mapList;
		categoryIndex = new ArrayList<Integer>();
		for(int i = 0; i < mapList.size(); i++){
			categoryIndex.add(0);
		}
	}
	
	public void setList(ArrayList<LinearLayout> _categoryList) {
		categoryList = _categoryList;
	}
	
	public View getNextFocus(View currentView, MovementDirection direction) {
		View next = currentView;
		switch(direction) {
			case UP:
				if(cancel) {
					cancel = false;
					next = activity.findViewById(mapList.get(current).get(categoryIndex.get(current)).getId());
				}
				else if(categoryIndex.get(current) > 0) {
					mapList.get(current).get(categoryIndex.get(current)).setVisibility(TrackButton.GONE);
					categoryIndex.set(current,categoryIndex.get(current)-1);
					mapList.get(current).get(categoryIndex.get(current)).setVisibility(TrackButton.VISIBLE);
					next = activity.findViewById(mapList.get(current).get(categoryIndex.get(current)).getId());
				}
				break;
			case DOWN:
				if(categoryIndex.get(current) < (mapList.get(current).size()-1) ) {
					mapList.get(current).get(categoryIndex.get(current)).setVisibility(TrackButton.GONE);
					categoryIndex.set(current,categoryIndex.get(current)+1);
					mapList.get(current).get(categoryIndex.get(current)).setVisibility(TrackButton.VISIBLE);
					next = activity.findViewById(mapList.get(current).get(categoryIndex.get(current)).getId());
				}
				else {
					cancel = true;
			        next = activity.findViewById(R.id.cancelBar);
				}
				break;
			case LEFT:
				if(!cancel) {
					LinearLayout parent = (LinearLayout)mapList.get(current).get(categoryIndex.get(current)).getParent();
					parent.getChildAt(1).setVisibility(ImageView.INVISIBLE);
					parent.getChildAt(parent.getChildCount()-1).setVisibility(ImageView.INVISIBLE);
					current--;
					pos--;
					if(current < 0) {
						current = mapList.size()-1;
					}
					
					if(pos < 0) {
						pos = 0;
						shiftRight();
					}
					LinearLayout parent2 = (LinearLayout)mapList.get(current).get(categoryIndex.get(current)).getParent();
					parent2.getChildAt(1).setVisibility(ImageView.VISIBLE);
					parent2.getChildAt(parent2.getChildCount()-1).setVisibility(ImageView.VISIBLE);
					next = activity.findViewById(mapList.get(current).get(categoryIndex.get(current)).getId());
				}
				break;
			case RIGHT:
				if(!cancel) {
					LinearLayout parent = (LinearLayout)mapList.get(current).get(categoryIndex.get(current)).getParent();
					parent.getChildAt(1).setVisibility(ImageView.INVISIBLE);
					parent.getChildAt(parent.getChildCount()-1).setVisibility(ImageView.INVISIBLE);
					current++;
					pos++;
					if(current >= mapList.size()) {
						current = 0;
					}
					
					if(pos > 3) {
						pos = 3;
						shiftLeft();
					}

					LinearLayout parent2 = (LinearLayout)mapList.get(current).get(categoryIndex.get(current)).getParent();
					parent2.getChildAt(1).setVisibility(ImageView.VISIBLE);
					parent2.getChildAt(parent2.getChildCount()-1).setVisibility(ImageView.VISIBLE);
					next = activity.findViewById(mapList.get(current).get(categoryIndex.get(current)).getId());
				}
				break;
			default:
				next = currentView;
				break;
		}
		
		return next;
	}

	public View getCurrent()
	{
		current = 0;
		System.out.println("THIS IS THE CURRENT " + current);
		return activity.findViewById(mapList.get(current).get(categoryIndex.get(current)).getId());
	}
	
	public void shiftRight() {
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.soundMenu);
		layout.removeAllViewsInLayout();
		layout.addView(larrow);
		for(int i=current; i<current+4 && i<categoryList.size(); i++){
	    	layout.addView(categoryList.get(i));	    	
	    }
		for(int i=0; i<(4-(categoryList.size()-current)); i++){
	    	layout.addView(categoryList.get(i));	    	
	    }
		layout.addView(rarrow);
	}
	
	public void shiftLeft() {
		LinearLayout layout = (LinearLayout) activity.findViewById(R.id.soundMenu);
		layout.removeAllViewsInLayout();
		layout.addView(larrow);
		if(current > 2) {
			for(int i=current-3; i<current+1; i++){
		    	layout.addView(categoryList.get(i));	    	
		    }
		}
		else {
			for(int i=categoryList.size()-3+current; i<categoryList.size(); i++){
		    	layout.addView(categoryList.get(i));	    	
		    }
			for(int i=0; i<current+1; i++){
		    	layout.addView(categoryList.get(i));	    	
		    }
		}
		layout.addView(rarrow);
	}
}
