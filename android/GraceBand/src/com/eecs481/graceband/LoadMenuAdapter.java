package com.eecs481.graceband;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadMenuAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> values;
	private int lastPos;
	
	public LoadMenuAdapter(Context context, ArrayList<String> values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = new ArrayList<String>(values);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.TextView1);
		textView.setText(values.get(position));
		return rowView;
	}
}