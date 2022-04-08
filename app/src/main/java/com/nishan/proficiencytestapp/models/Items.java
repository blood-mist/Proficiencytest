package com.nishan.proficiencytestapp.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Items{
	public Items ( String title, List<RowsItem> rows){
		this.title=title;
		this.rows=rows;
	}
	@SerializedName("title")
	private String title;

	@SerializedName("rows")
	private List<RowsItem> rows;

	public String getTitle(){
		return title;
	}

	public List<RowsItem> getRows(){
		return rows;
	}
}