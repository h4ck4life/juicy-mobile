package com.sli.juicymobile.data;

public class Battery {

	public String brand, type, model, size;
	public int mAh;
	public float amp_limit, safe;

	public Battery(String brand, String type, String model, String size,
			int mAh, float amp_limit, float safe) {
		this.brand = brand;
		this.type = type;
		this.model = model;
		this.size = size;
		this.mAh = mAh;
		this.amp_limit = amp_limit;
		this.safe = safe;
	}

	public String toString() {
		String s = this.brand;
		if (this.model.equals("")) {
			s = s + " " + this.size + " " + Integer.toString(this.mAh) + "mAh";
		} else {
			s = s + " " + this.model;
		}

		return s;
	}

}
