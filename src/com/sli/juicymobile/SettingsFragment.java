package com.sli.juicymobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

		adapter = new BatteryArrayAdapter(getSherlockActivity(),
				android.R.layout.simple_spinner_item);
		spBattery = (Spinner) v.findViewById(R.id.spBattery);
		spBattery.setAdapter(adapter);

		/*spBattery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View v,
					int position, long id) {
				// save position in preferenes
			}

		});*/

		return v;
	}

}
