package com.sli.juicymobile.db;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BatteryArrayAdapter extends ArrayAdapter<Battery> {
	
	public static Battery[] batteries = {
		// AW
		new Battery("AW", "IMR", "", "18650", 2000, 1f,    0.5f),
		new Battery("AW", "IMR", "", "18650", 1600, 24,    0.25f),
		new Battery("AW", "IMR", "", "18350", 700,  6,     0.8f),
		new Battery("AW", "IMR", "", "18490", 1100, 16.5f, 0.3f),
		
		// Efest
		new Battery("Efest", "IMR", "", "18650", 1500, 15,   0.35f),
		new Battery("Efest", "IMR", "", "18650", 2250, 10,   0.5f),
		new Battery("Efest", "IMR", "", "18650", 1600, 30,   0.2f),
		new Battery("Efest", "IMR", "", "18350", 800,  6.4f, 0.75f),
		new Battery("Efest", "IMR", "", "18490", 1100, 8.8f, 0.55f),
		new Battery("Efest", "IMR", "", "18500", 1100, 10,   0.5f),
		
		// EH
		new Battery("EH", "IMR", "", "18650", 2000, 16,   0.3f),
		new Battery("EH", "IMR", "", "18650", 2250, 18,   0.3f),
		new Battery("EH", "IMR", "", "18650", 1600, 30,   0.2f),
		new Battery("EH", "IMR", "", "18350", 800,  6.4f, 0.75f),
		new Battery("EH", "IMR", "", "18500", 1100, 8.8f, 0.55f),
		
		// MNKE
		new Battery("MNKE", "IMR", "", "18650", 1500, 20, 0.25f),
		
		// Orbtronic
		new Battery("Orbtronic", "IMR", "CGR18650CH",   "18650", 2250, 10,   0.5f),
		new Battery("Orbtronic", "IMR", "18650 PD2900", "18650", 2900, 10,   0.5f),
		new Battery("Orbtronic", "IMR", "18650 SX22",   "18650", 2000, 22,   0.25f),
		new Battery("Orbtronic", "IMR", "18650 SX30",   "18650", 2100, 30,   0.2f),
		new Battery("Orbtronic", "Li",  "",             "18650", 3600, 5,    1.0f),
		new Battery("Orbtronic", "Li",  "NCR18650A",    "18650", 3100, 6.2f, 0.8f),
		
		// Panasonic
		new Battery("Panasonic", "IMR", "CGR18650CH", "18650", 2250, 10,   0.5f),
		new Battery("Panasonic", "IMR", "NCR18650PF", "18650", 2900, 10,   0.5f),
		new Battery("Panasonic", "IMR", "NCR18650PD", "18650", 2900, 10,   0.5f),
		new Battery("Panasonic", "Li",  "NCR18650A",  "18650", 3100, 6.2f, 0.8f),
		new Battery("Panasonic", "Li",  "NCR18650B",  "18650", 3400, 6.8f, 0.75f),
		
		// Samsung
		new Battery("Samsung", "IMR", "INR18650-20R", "18650", 2000, 22, 0.25f),
		
		// Sanyo
		new Battery("Sanyo", "IMR", "UR18650EX", "18650", 2000, 20, 0.25f),
		
		// Sony
		new Battery("Sony", "IMR", "US18650V3",   "18650", 2250, 10, 0.5f),
		new Battery("Sony", "IMR", "US18650VTC3", "18650", 1600, 30, 0.2f),
		new Battery("Sony", "IMR", "US18650VTC4", "18650", 2100, 30, 0.2f),
	};
	
	private Context context;

	public BatteryArrayAdapter(Context context, int resource) {
		super(context, android.R.layout.simple_spinner_dropdown_item, batteries);
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);
		label.setText(batteries[position].toString());
		return label;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		TextView label = new TextView(context);
		label.setText(batteries[position].toString());
		return label;
	}

}
