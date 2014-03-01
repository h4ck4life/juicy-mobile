package com.sli.juicymobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.sli.juicymobile.data.WireArrayAdapter;

public class CoilBuilderFragment extends SherlockFragment {

	private Spinner spWireType, spCoilType;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstance) {

		View v = inflater.inflate(R.layout.coil_builder_fragment, container,
				false);

		// Get battery preference
		final SharedPreferences prefs = getSherlockActivity()
				.getSharedPreferences("com.sli.juicymobile",
						Context.MODE_PRIVATE);
		final int wirePref = prefs.getInt("wire", -1);

		// Get coil type preference
		final int coilPref = prefs.getInt("coil", -1);

		WireArrayAdapter adapter = new WireArrayAdapter(getSherlockActivity(),
				R.layout.spinner_item);
		spWireType = (Spinner) v.findViewById(R.id.spWireType);
		spWireType.setAdapter(adapter);

		// Yep. Another one of these things.
		final OnItemSelectedListener spWireListener = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("wire", position);
				editor.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}

		};

		spWireType.post(new Runnable() {
			public void run() {
				spWireType.setOnItemSelectedListener(spWireListener);

				if (wirePref >= 0 && wirePref < spWireType.getCount()) {
					spWireType.setSelection(wirePref);
				}
			}
		});

		// Setup coil type spinner and set selection if valid
		spCoilType = (Spinner) v.findViewById(R.id.spCoilType);
		ArrayAdapter<CharSequence> pageAdapter = ArrayAdapter
				.createFromResource(getSherlockActivity(), R.array.coils,
						R.layout.spinner_item);
		spCoilType.setAdapter(pageAdapter);

		// One more
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

		spCoilType.post(new Runnable() {
			public void run() {
				spCoilType.setOnItemSelectedListener(spPageListener);

				if (coilPref >= 0 && coilPref < spCoilType.getCount()) {
					spCoilType.setSelection(coilPref);
				}
			}
		});

		return v;

	}

}
