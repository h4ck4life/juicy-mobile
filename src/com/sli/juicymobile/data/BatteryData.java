package com.sli.juicymobile.data;

public class BatteryData {
	public static String[] Names = { "Panasonic CGR18650CH", "Efest IMR 18350" };

	public static float[] AmpLimits = { 10f, // Panasonic CGR18650CH
			7.4f // Efest IMR 18350
	};

	public static float[] PulseAmpLimits = { 0, // Panasonic CGR18650CH
			0 // Efest IMR 18350
	};

	public static int[] mAh = { 2250, // Panasonic CGR18650CH
			800 // Efest IMR 18350
	};

	/**
	 * Returns an array of supported batteryes for use with an array adapter.
	 * 
	 * @return array of battery names with mAh ratings
	 */
	public static String[] batteryNames() {
		String[] names = {};

		for (int i = 0; i < Names.length; i++) {
			names[i] = Names[i] + " (" + Float.toString(mAh[i]) + ")";
		}

		return names;
	}
}
