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
		int batteryPref = prefs.getInt("battery", -1);

		// Get starting page preference
		int pagePref = prefs.getInt("startingPage", 0);

		BatteryArrayAdapter batteryAdapter = new BatteryArrayAdapter(
				getSherlockActivity(),
				R.layout.spinner_item);
		spBattery = (Spinner) v.findViewById(R.id.spBattery);
		spBattery.setAdapter(batteryAdapter);

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
			public void onNothingSelected(AdapterView<?> adapterView) {
			}

		});

		spStartingPage = (Spinner) v.findViewById(R.id.spStartingPage);
		ArrayAdapter<CharSequence> pageAdapter = ArrayAdapter
				.createFromResource(getSherlockActivity(), R.array.pages,
						R.layout.spinner_item);
		spStartingPage.setAdapter(pageAdapter);
		spStartingPage.setPadding(16, 16, 16, 16);

		if (pagePref >= 0) {
			spBattery.setSelection(pagePref);
		}

		spStartingPage.setOnItemSelectedListener(new OnItemSelectedListener() {

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

		});

		return v;
	}

}
