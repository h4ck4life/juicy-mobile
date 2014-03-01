package com.sli.juicymobile.data;

import com.sli.juicymobile.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WireArrayAdapter extends ArrayAdapter<Wire> {

	public static Wire[] wires = {
		// @formatter:off
		// Round Kanthal
		new Wire("A1 Kanthal", 20, 0.81f,    0.81f),
		new Wire("A1 Kanthal", 22, 0.64f,    1.31f),
		new Wire("A1 Kanthal", 24, 0.51f,    2.04f),
		new Wire("A1 Kanthal", 26, 0.40386f, 3.21f),
		new Wire("A1 Kanthal", 28, 0.32004f, 5.27f),
		new Wire("A1 Kanthal", 30, 0.254f,   8.36f),
		new Wire("A1 Kanthal", 32, 0.2032f,  13.1f),
		new Wire("A1 Kanthal", 34, 0.16002f, 21.1f),
		
		// Ribbon Kanthal
		new Wire("Ribbon Kanthal", 0, 0.8f, 5.76f),
		new Wire("Ribbon Kanthal", 0, 0.5f, 8.52f),
		new Wire("Ribbon Kanthal", 0, 0.4f, 11.4f),
		
		// Nickel
		new Wire("Nickel", 28, 0f, 0.3778f),
		new Wire("Nickel", 30, 0f, 0.6f),
		new Wire("Nickel", 32, 0f, 0.9375f)
		// @formatter:on
	};

	private Context context;

	public WireArrayAdapter(Context context, int resource) {
		super(context, resource, wires);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	private View getCustomView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(this.context);
		TextView v = (TextView) inflater.inflate(R.layout.spinner_item, parent,
				false);

		v.setText(wires[position].toString());

		return v;
	}

}
