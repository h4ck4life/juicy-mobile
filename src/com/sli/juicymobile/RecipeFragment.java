package com.sli.juicymobile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.thinkti.android.filechooser.FileChooser;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeFragment extends SherlockFragment {
	
	private static final int FILE_CHOOSER = 1;

	private EditText etAmountToMake;
	private EditText etFlavor1Name, etFlavor2Name, etFlavor3Name,
			etFlavor4Name, etFlavor5Name, etFlavor6Name, etFlavor7Name;
	private EditText etFlavor1Percent, etFlavor2Percent, etFlavor3Percent,
			etFlavor4Percent, etFlavor5Percent, etFlavor6Percent,
			etFlavor7Percent;
	private WebView wvRecipe;
	private Button bCalculate, bClear;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.recipe_fragment, container, false);

		etAmountToMake = (EditText) v.findViewById(R.id.etAmountToMake);
		etFlavor1Name = (EditText) v.findViewById(R.id.etFlavor1Name);
		etFlavor2Name = (EditText) v.findViewById(R.id.etFlavor2Name);
		etFlavor3Name = (EditText) v.findViewById(R.id.etFlavor3Name);
		etFlavor4Name = (EditText) v.findViewById(R.id.etFlavor4Name);
		etFlavor5Name = (EditText) v.findViewById(R.id.etFlavor5Name);
		etFlavor6Name = (EditText) v.findViewById(R.id.etFlavor6Name);
		etFlavor7Name = (EditText) v.findViewById(R.id.etFlavor7Name);
		etFlavor1Percent = (EditText) v.findViewById(R.id.etFlavor1Percent);
		etFlavor2Percent = (EditText) v.findViewById(R.id.etFlavor2Percent);
		etFlavor3Percent = (EditText) v.findViewById(R.id.etFlavor3Percent);
		etFlavor4Percent = (EditText) v.findViewById(R.id.etFlavor4Percent);
		etFlavor5Percent = (EditText) v.findViewById(R.id.etFlavor5Percent);
		etFlavor6Percent = (EditText) v.findViewById(R.id.etFlavor6Percent);
		etFlavor7Percent = (EditText) v.findViewById(R.id.etFlavor7Percent);

		wvRecipe = (WebView) v.findViewById(R.id.wvRecipe);
		wvRecipe.setBackgroundColor(0x00000000);

		bCalculate = (Button) v.findViewById(R.id.bCalculate);
		bCalculate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String display = calculateRecipe();
				wvRecipe.loadData(display, "text/html", "utf-8");
			}
		});

		bClear = (Button) v.findViewById(R.id.bClearRecipe);
		bClear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				etAmountToMake.setText("");
				etFlavor1Name.setText("");
				etFlavor2Name.setText("");
				etFlavor3Name.setText("");
				etFlavor4Name.setText("");
				etFlavor5Name.setText("");
				etFlavor6Name.setText("");
				etFlavor7Name.setText("");
				etFlavor1Percent.setText("");
				etFlavor2Percent.setText("");
				etFlavor3Percent.setText("");
				etFlavor4Percent.setText("");
				etFlavor5Percent.setText("");
				etFlavor6Percent.setText("");
				etFlavor7Percent.setText("");
				wvRecipe.loadData("", "text/plain", "utf-8");
			}

		});

		setHasOptionsMenu(true);

		return v;

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.recipe, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_save:
			// export to json
			saveRecipe();
			break;
		case R.id.menu_open:
			// import from json
			openRecipe();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private String calculateRecipe() {
		float amt, finalAmt, f1per = 0f, f2per = 0f, f3per = 0f, f4per = 0f, f5per = 0f, f6per = 0f, f7per = 0f;
		float f1amt = 0f, f2amt = 0f, f3amt = 0f, f4amt = 0f, f5amt = 0f, f6amt = 0f, f7amt = 0f;
		String display = "";

		if (etAmountToMake.getText().toString().equals("")) {
			// ask for amount to make
			Toast.makeText(getSherlockActivity(),
					"You must specify an amount.", Toast.LENGTH_SHORT).show();
		} else {
			// run calculation
			amt = Float.parseFloat(etAmountToMake.getText().toString());

			if (!etFlavor1Percent.getText().toString().equals("")) {
				f1per = Float.parseFloat(etFlavor1Percent.getText().toString());
				f1amt = amt * (f1per / 100);
			}

			if (!etFlavor2Percent.getText().toString().equals("")) {
				f2per = Float.parseFloat(etFlavor2Percent.getText().toString());
				f2amt = amt * (f2per / 100);
			}

			if (!etFlavor3Percent.getText().toString().equals("")) {
				f3per = Float.parseFloat(etFlavor3Percent.getText().toString());
				f3amt = amt * (f3per / 100);
			}

			if (!etFlavor4Percent.getText().toString().equals("")) {
				f4per = Float.parseFloat(etFlavor4Percent.getText().toString());
				f4amt = amt * (f4per / 100);
			}

			if (!etFlavor5Percent.getText().toString().equals("")) {
				f5per = Float.parseFloat(etFlavor5Percent.getText().toString());
				f5amt = amt * (f5per / 100);
			}

			if (!etFlavor6Percent.getText().toString().equals("")) {
				f6per = Float.parseFloat(etFlavor6Percent.getText().toString());
				f6amt = amt * (f6per / 100);
			}

			if (!etFlavor7Percent.getText().toString().equals("")) {
				f7per = Float.parseFloat(etFlavor7Percent.getText().toString());
				f7amt = amt * (f7per / 100);
			}

			finalAmt = amt
					- (f1amt + f2amt + f3amt + f4amt + f5amt + f6amt + f7amt);

			display = "<html><body>";
			display += "<style>body{color:white}table{border-collapse:collapse}td{text-align:center}</style>";
			display += "<table border='1' width='100%'><tr><th>Ingredient</th><th>Amount</th></tr>";
			display += "<tr><td>Base</td><td>" + Float.toString(finalAmt)
					+ "mL</td></tr>";

			if (f1per > 0f) {
				display += "<tr><td>";
				if (!etFlavor1Name.getText().toString().equals("")) {
					display += etFlavor1Name.getText().toString();
				} else {
					display += "Flavor 1";
				}
				display += "</td><td>" + String.format("%.4f", f1amt)
						+ "mL</td></tr>";
			}

			if (f2per > 0f) {
				display += "<tr><td>";
				if (!etFlavor2Name.getText().toString().equals("")) {
					display += etFlavor2Name.getText().toString();
				} else {
					display += "Flavor 2";
				}
				display += "</td><td>" + String.format("%.4f", f2amt)
						+ "mL</td></tr>";
			}

			if (f3per > 0f) {
				display += "<tr><td>";
				if (!etFlavor3Name.getText().toString().equals("")) {
					display += etFlavor3Name.getText().toString();
				} else {
					display += "Flavor 3";
				}
				display += "</td><td>" + String.format("%.4f", f3amt)
						+ "mL</td></tr>";
			}

			if (f4per > 0f) {
				display += "<tr><td>";
				if (!etFlavor4Name.getText().toString().equals("")) {
					display += etFlavor4Name.getText().toString();
				} else {
					display += "Flavor 4";
				}
				display += "</td><td>" + String.format("%.4f", f4amt)
						+ "mL</td></tr>";
			}

			if (f5per > 0f) {
				display += "<tr><td>";
				if (!etFlavor5Name.getText().toString().equals("")) {
					display += etFlavor5Name.getText().toString();
				} else {
					display += "Flavor 5";
				}
				display += "</td><td>" + String.format("%.4f", f5amt)
						+ "mL</td></tr>";
			}

			if (f6per > 0f) {
				display += "<tr><td>";
				if (!etFlavor6Name.getText().toString().equals("")) {
					display += etFlavor6Name.getText().toString();
				} else {
					display += "Flavor 6";
				}
				display += "</td><td>" + String.format("%.4f", f6amt)
						+ "mL</td></tr>";
			}

			if (f7per > 0f) {
				display += "<tr><td>";
				if (!etFlavor7Name.getText().toString().equals("")) {
					display += etFlavor7Name.getText().toString();
				} else {
					display += "Flavor 7";
				}
				display += "</td><td>" + String.format("%.4f", f7amt)
						+ "mL</td></tr>";
			}

			display += "</table></body></html>";
		}

		return display;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == FILE_CHOOSER && resultCode == -1) {
			// Get filename, open, read, and insert into the form
			String fileSelected = data.getStringExtra("fileSelected");
			File path = new File(fileSelected);
			StringBuilder sb = new StringBuilder();

			try {
				BufferedReader br = new BufferedReader(new FileReader(path));
				String line;

				try {
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			setRecipeFromJSON(sb.toString());
		}
	}

	/*
	 * Menu callback... things.
	 */
	private void saveRecipe() {
		final EditText n = new EditText(getSherlockActivity());

		new AlertDialog.Builder(getSherlockActivity())
				.setTitle("Save")
				.setMessage("Input name for this recipe.")
				.setView(n)
				.setPositiveButton("Save",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								/*
								 * Build Juicy-compatible JSON file and export
								 * to configured save location.
								 */
								String name = n.getText().toString();

								JSONObject recipe = getRecipeAsJSON();

								// Write JSON file
								String state = Environment
										.getExternalStorageState();
								if (Environment.MEDIA_MOUNTED.equals(state)) {
									// Create directory for storing recipes if needed
									File outPath = new File(Environment.getExternalStorageDirectory() + "/Juicy");
									if (!outPath.exists()) {
										if (outPath.mkdir()) {
											Log.i("Juicy", "Recipe directory created.");
										} else {
											Log.e("Juicy", "Recipe directory failed to be created.");
										}
									} else {
										Log.i("Juicy", "Recipe directory already exists.");
									}
									File outFile = new File(outPath, name
											+ ".json");
									try {
										FileOutputStream fos = new FileOutputStream(
												outFile);
										try {
											try {
												fos.write(recipe.toString(2)
														.getBytes());
											} catch (JSONException e) {
												e.printStackTrace();
											}
											fos.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}

									Toast.makeText(
											getSherlockActivity(),
											outFile.getAbsolutePath()
													.toString(),
											Toast.LENGTH_SHORT).show();
								}
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// nothing, lol!
							}
						}).show();
	}

	private void openRecipe() {
		Intent i = new Intent(getSherlockActivity(), FileChooser.class);
		ArrayList<String> extensions = new ArrayList<String>();
		extensions.add(".json");
		i.putStringArrayListExtra("filterFileExtension", extensions);

		String startingDirectory = Environment.getExternalStorageDirectory()
				.getAbsolutePath().toString()
				+ "/Juicy/";
		i.putExtra("startingDirectory", startingDirectory);

		startActivityForResult(i, FILE_CHOOSER);
	}

	/*
	 * Generate a JSONObject from the recipe form
	 */
	private JSONObject getRecipeAsJSON() {
		JSONObject recipe = new JSONObject();

		try {
			// Recipe base solution
			recipe.put("nicotine_base", 0.0);
			recipe.put("nicotine_target", 0.0);
			recipe.put("amount",
					Float.parseFloat(etAmountToMake.getText().toString()));
			recipe.put("cut", 0.0);
			recipe.put("target_pg", 0.0);
			recipe.put("target_vg", 0.0);
			recipe.put("base_pg_percent", 0.0);
			recipe.put("base_vg_percent", 0.0);
			recipe.put("drops_per_ml", 0.0);
			recipe.put("Notes", "");
			recipe.put("mlNotes", "");

			JSONArray flavors = new JSONArray();

			// Flavor 1
			if (!etFlavor1Name.getText().toString().equals("")
					|| !etFlavor1Name.getText().toString().equals("")) {
				JSONObject f = new JSONObject();
				f.put("name", etFlavor1Name.getText().toString());
				f.put("percent",
						Float.parseFloat(etFlavor1Percent.getText().toString()));
				f.put("pg_percent", 100.0);
				f.put("vg_percent", 0.0);
				f.put("flavorless", false);
				flavors.put(f);
			}

			// Flavor 2
			if (!etFlavor2Name.getText().toString().equals("")
					|| !etFlavor2Name.getText().toString().equals("")) {
				JSONObject f = new JSONObject();
				f.put("name", etFlavor2Name.getText().toString());
				f.put("percent",
						Float.parseFloat(etFlavor2Percent.getText().toString()));
				f.put("pg_percent", 100.0);
				f.put("vg_percent", 0.0);
				f.put("flavorless", false);
				flavors.put(f);
			}

			// Flavor 3
			if (!etFlavor3Name.getText().toString().equals("")
					|| !etFlavor3Name.getText().toString().equals("")) {
				JSONObject f = new JSONObject();
				f.put("name", etFlavor3Name.getText().toString());
				f.put("percent",
						Float.parseFloat(etFlavor3Percent.getText().toString()));
				f.put("pg_percent", 100.0);
				f.put("vg_percent", 0.0);
				f.put("flavorless", false);
				flavors.put(f);
			}

			// Flavor 4
			if (!etFlavor4Name.getText().toString().equals("")
					|| !etFlavor4Name.getText().toString().equals("")) {
				JSONObject f = new JSONObject();
				f.put("name", etFlavor4Name.getText().toString());
				f.put("percent",
						Float.parseFloat(etFlavor4Percent.getText().toString()));
				f.put("pg_percent", 100.0);
				f.put("vg_percent", 0.0);
				f.put("flavorless", false);
				flavors.put(f);
			}

			// Flavor 5
			if (!etFlavor5Name.getText().toString().equals("")
					|| !etFlavor5Name.getText().toString().equals("")) {
				JSONObject f = new JSONObject();
				f.put("name", etFlavor5Name.getText().toString());
				f.put("percent",
						Float.parseFloat(etFlavor5Percent.getText().toString()));
				f.put("pg_percent", 100.0);
				f.put("vg_percent", 0.0);
				f.put("flavorless", false);
				flavors.put(f);
			}

			// Flavor 6
			if (!etFlavor6Name.getText().toString().equals("")
					|| !etFlavor6Name.getText().toString().equals("")) {
				JSONObject f = new JSONObject();
				f.put("name", etFlavor6Name.getText().toString());
				f.put("percent",
						Float.parseFloat(etFlavor6Percent.getText().toString()));
				f.put("pg_percent", 100.0);
				f.put("vg_percent", 0.0);
				f.put("flavorless", false);
				flavors.put(f);
			}

			// Flavor 7
			if (!etFlavor7Name.getText().toString().equals("")
					|| !etFlavor7Name.getText().toString().equals("")) {
				JSONObject f = new JSONObject();
				f.put("name", etFlavor7Name.getText().toString());
				f.put("percent",
						Float.parseFloat(etFlavor7Percent.getText().toString()));
				f.put("pg_percent", 100.0);
				f.put("vg_percent", 0.0);
				f.put("flavorless", false);
				flavors.put(f);
			}

			recipe.put("flavors", flavors);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return recipe;
	}

	private void setRecipeFromJSON(String rawJSON) {
		try {
			JSONObject json = new JSONObject(rawJSON);
			JSONArray flavors = new JSONArray();

			etAmountToMake.setText(json.getString("amount"));
			flavors = json.getJSONArray("flavors");

			JSONObject f = null;

			// Flavor 1
			if (flavors.length() > 0) {
				f = flavors.getJSONObject(0);
				etFlavor1Name.setText(f.getString("name"));
				etFlavor1Percent.setText(f.getString("percent"));
			}

			// Flavor 2
			if (flavors.length() > 1) {
				f = flavors.getJSONObject(1);
				etFlavor2Name.setText(f.getString("name"));
				etFlavor2Percent.setText(f.getString("percent"));
			}

			// Flavor 3
			if (flavors.length() > 2) {
				f = flavors.getJSONObject(2);
				etFlavor3Name.setText(f.getString("name"));
				etFlavor3Percent.setText(f.getString("percent"));
			}

			// Flavor 4
			if (flavors.length() > 3) {
				f = flavors.getJSONObject(3);
				etFlavor4Name.setText(f.getString("name"));
				etFlavor4Percent.setText(f.getString("percent"));
			}

			// Flavor 5
			if (flavors.length() > 4) {
				f = flavors.getJSONObject(4);
				etFlavor5Name.setText(f.getString("name"));
				etFlavor5Percent.setText(f.getString("percent"));
			}

			// Flavor 6
			if (flavors.length() > 5) {
				f = flavors.getJSONObject(5);
				etFlavor6Name.setText(f.getString("name"));
				etFlavor6Percent.setText(f.getString("percent"));
			}

			// Flavor 7
			if (flavors.length() > 6) {
				f = flavors.getJSONObject(6);
				etFlavor7Name.setText(f.getString("name"));
				etFlavor7Percent.setText(f.getString("percent"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
