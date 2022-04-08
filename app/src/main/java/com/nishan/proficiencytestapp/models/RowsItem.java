package com.nishan.proficiencytestapp.models;

import com.google.gson.annotations.SerializedName;

public class RowsItem{

	@SerializedName("imageHref")
	private String imageHref;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	public String getImageHref(){
		return imageHref;
	}

	public String getDescription(){
		return description;
	}

	public String getTitle(){
		return title;
	}

	public void setImageHref(String imageHref) {
		this.imageHref = imageHref;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}