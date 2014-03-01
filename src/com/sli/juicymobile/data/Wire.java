package com.sli.juicymobile.data;

public class Wire {
	String type;
	int gauge;
	float size, resistance;

	public Wire(String type, int gauge, float size, float resistance) {
		this.type = type;
		this.gauge = gauge;
		this.size = size;
		this.resistance = resistance;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGauge() {
		return this.gauge;
	}

	public void setGauge(int gauge) {
		this.gauge = gauge;
	}

	public float getSize() {
		return this.size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public float getResistance() {
		return this.resistance;
	}

	public void setResistance(float resistance) {
		this.resistance = resistance;
	}

	@Override
	public String toString() {
		String s = this.type + " ";

		if (this.gauge == 0) {
			s = s + Float.toString(this.size) + "mm";
		} else {
			s = s + Integer.toString(this.gauge) + "g";
		}

		return s;
	}

}