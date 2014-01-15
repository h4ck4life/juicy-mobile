package com.sli.juicymobile;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResistanceFragment extends SherlockFragment {

	private EditText etResistance1, etResistance2, etResistance3,
			etResistance4;
	private Button bCalculate, bClearRes;
	private TextView tvOutput;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.resistance_fragment, container,
				false);

		etResistance1 = (EditText) v.findViewById(R.id.etResistance1);
		etResistance2 = (EditText) v.findViewById(R.id.etResistance2);
		etResistance3 = (EditText) v.findViewById(R.id.etResistance3);
		etResistance4 = (EditText) v.findViewById(R.id.etResistance4);
		bCalculate = (Button) v.findViewById(R.id.bCalculate);
		bClearRes = (Button) v.findViewById(R.id.bClearRes);
		tvOutput = (TextView) v.findViewById(R.id.tvOutput);

		bCalculate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Calculate total resistance
				float coil1 = 0f, coil2 = 0f, coil3 = 0f, coil4 = 0f, coilTotal = 0f, totalRes;
				String coil;

				coil = etResistance1.getText().toString();
				if (!coil.matches("")) {
					coil1 = Float.parseFloat(coil);
					coilTotal += 1 / coil1;
				}

				coil = etResistance2.getText().toString();
				if (!coil.matches("")) {
					coil2 = Float.parseFloat(coil);
					coilTotal += 1 / coil2;
				}

				coil = etResistance3.getText().toString();
				if (!coil.matches("")) {
					coil3 = Float.parseFloat(coil);
					coilTotal += 1 / coil3;
				}

				coil = etResistance4.getText().toString();
				if (!coil.matches("")) {
					coil4 = Float.parseFloat(coil);
					coilTotal += 1 / coil4;
				}

				totalRes = 1 / coilTotal;
				tvOutput.setText(Float.toString(totalRes)
						+ getString(R.string.ohm_symbol));
			}

		});

		bClearRes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				etResistance1.setText("");
				etResistance2.setText("");
				etResistance3.setText("");
				etResistance4.setText("");
				tvOutput.setText("");
			}
		});

		return v;
	}

}
