package com.sli.juicymobile;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecipeFragment extends SherlockFragment {

	private EditText etAmountToMake;
	private EditText etFlavor1Name, etFlavor2Name, etFlavor3Name,
			etFlavor4Name;
	private EditText etFlavor1Percent, etFlavor2Percent, etFlavor3Percent,
			etFlavor4Percent;
	private WebView wvRecipe;
	private Button bCalculate, bClear;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.recipe_fragment, container, false);

		// Main activity - should be moved now that fragments are used
		etAmountToMake = (EditText) v.findViewById(R.id.etAmountToMake);
		etFlavor1Name = (EditText) v.findViewById(R.id.etFlavor1Name);
		etFlavor2Name = (EditText) v.findViewById(R.id.etFlavor2Name);
		etFlavor3Name = (EditText) v.findViewById(R.id.etFlavor3Name);
		etFlavor4Name = (EditText) v.findViewById(R.id.etFlavor4Name);
		etFlavor1Percent = (EditText) v.findViewById(R.id.etFlavor1Percent);
		etFlavor2Percent = (EditText) v.findViewById(R.id.etFlavor2Percent);
		etFlavor3Percent = (EditText) v.findViewById(R.id.etFlavor3Percent);
		etFlavor4Percent = (EditText) v.findViewById(R.id.etFlavor4Percent);

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
				etFlavor1Percent.setText("");
				etFlavor2Percent.setText("");
				etFlavor3Percent.setText("");
				etFlavor4Percent.setText("");
				wvRecipe.loadData("", "text/plain", "utf-8");
			}

		});

		setHasOptionsMenu(true);

		return v;

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_save:
			// save item to SQLite database
			saveRecipe();
			break;
		case R.id.menu_open:
			// open SQLite database and list recipes
			openRecipe();
			break;
		case R.id.menu_export:
			// export to json
			exportRecipe();
			break;
		case R.id.menu_import:
			// import from json
			importRecipe();
			break;
		case R.id.menu_settings:
			// open settings
			Toast.makeText(getSherlockActivity(), "lol settings",
					Toast.LENGTH_SHORT).show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private String calculateRecipe() {
		float amt, finalAmt, f1per = 0f, f2per = 0f, f3per = 0f, f4per = 0f;
		float f1amt = 0f, f2amt = 0f, f3amt = 0f, f4amt = 0f;
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

			finalAmt = amt - (f1amt + f2amt + f3amt + f4amt);

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

			display += "</table></body></html>";
		}

		return display;
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
								String name = n.getText().toString();
								// insert or update, just Toast it for now
								Toast.makeText(getSherlockActivity(),
										"Saved as " + name, Toast.LENGTH_SHORT)
										.show();
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

	}

	private void exportRecipe() {
		final EditText n = new EditText(getSherlockActivity());

		new AlertDialog.Builder(getSherlockActivity())
				.setTitle("Export")
				.setMessage("Input name for this recipe.")
				.setView(n)
				.setPositiveButton("Export",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String name = n.getText().toString();
								// save to filename
								Toast.makeText(getSherlockActivity(),
										"Exported as " + name + ".json",
										Toast.LENGTH_SHORT).show();
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

	private void importRecipe() {

	}

}
