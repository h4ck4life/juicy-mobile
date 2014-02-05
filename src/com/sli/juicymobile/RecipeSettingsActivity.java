package com.sli.juicymobile;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

public class RecipeSettingsActivity extends SherlockPreferenceActivity {
	
	static final String SAVE_LOCATION = "pref_saveLocation";
	static final String NIGHT_MODE = "pref_night";
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		addPreferencesFromResource(R.xml.preferences);
	}
}
