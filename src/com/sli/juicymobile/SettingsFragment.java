package com.sli.juicymobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.sli.juicymobile.db.BatteryArrayAdapter;

public class SettingsFragment extends SherlockFragment {

	private Spinner spBattery;
	private BatteryArrayAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.settings_fragment, container, false);

		// Get battery preference
		final SharedPreferences prefs = getSherlockActivity().getSharedPreferences(
				"com.sli.juicymobile", Context.MODE_PRIVATE);
		int batteryPref = prefs.getInt("battery", -1);

		adapter = new BatteryArrayAdapter(getSherlockActivity(),
				android.R.layout.simple_spinner_dropdown_item);
		spBattery = (Spinner) v.findViewById(R.id.spBattery);
		spBattery.setAdapter(adapter);
		
		if (batteryPref >= 0) {
			spBattery.setSelection(batteryPref);
		}
		
		spBattery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("battery", position);
				editor.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) { }
			
		});

		return v;
	}

}
