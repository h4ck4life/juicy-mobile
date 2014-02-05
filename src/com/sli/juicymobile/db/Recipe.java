package com.sli.juicymobile.db;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "recipes")
public class Recipe {

	@DatabaseField(generatedId = true)
	public int id;

	@DatabaseField
	public String name;
	
	@DatabaseField
	public String data;

	public Recipe() {
	}
	
	public Recipe(String name) {
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public JSONObject getData() {
		JSONObject d = null;
		try {
			d = new JSONObject(this.data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	public void setData(JSONObject data) {
		this.data = data.toString();
	}

}
