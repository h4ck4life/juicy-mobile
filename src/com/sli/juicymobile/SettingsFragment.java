package com.sli.juicymobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.sli.juicymobile.db.BatteryArrayAdapter;

public class SettingsFragment extends SherlockFragment {

	private Spinner spBattery, spStartingPage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.settings_fragment, container, false);

		// Get battery preference
		final SharedPreferences prefs = getSherlockActivity()
				.getSharedPreferences("com.sli.juicymobile",
						Context.MODE_PRIVATE);
		final int batteryPref = prefs.getInt("battery", -1);

		// Get starting page preference
		final int pagePref = prefs.getInt("startingPage", -1);

		// Setup battery spinner and set selection if valid
		BatteryArrayAdapter batteryAdapter = new BatteryArrayAdapter(
				getSherlockActivity(), R.layout.spinner_item);
		spBattery = (Spinner) v.findViewById(R.id.spBattery);
		spBattery.setAdapter(batteryAdapter);

		// This insanity prevents the settings from being reset by what
		// I consider to be some Android weirdness.
		final OnItemSelectedListener spBatteryListener = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("battery", position);
				editor.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}

		};

		spBattery.post(new Runnable() {
			public void run() {
				spBattery.setOnItemSelectedListener(spBatteryListener);

				if (batteryPref >= 0 && batteryPref < spBattery.getCount()) {
					spBattery.setSelection(batteryPref);
				}
			}
		});

		// Setup starting page spinner and set selection if valid
		spStartingPage = (Spinner) v.findViewById(R.id.spStartingPage);
		ArrayAdapter<CharSequence> pageAdapter = ArrayAdapter
				.createFromResource(getSherlockActivity(), R.array.pages,
						R.layout.spinner_item);
		spStartingPage.setAdapter(pageAdapter);

		// This insanity prevents the settings from being reset by what
		// I consider to be some Android weirdness.
		final OnItemSelectedListener spPageListener = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("startingPage", position);
				editor.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}

		};

		spStartingPage.post(new Runnable() {
			public void run() {
				spStartingPage.setOnItemSelectedListener(spPageListener);

				if (pagePref >= 0 && pagePref < spStartingPage.getCount()) {
					spStartingPage.setSelection(pagePref);
				}
			}
		});

		return v;
	}

}
