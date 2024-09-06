package com.washed.dev.roofus.to;

import java.util.ArrayList;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonValue;
@JsonComponent
public class RelatePhotosRequest {
	private ArrayList<String> photos;
	private String lineItemId;

	public ArrayList<String> getPhotos() {
		return photos;
	}
	
	public void setPhotos(ArrayList<String> photos) {
		this.photos = photos;
	}

	public String getLineItemId() {
		return lineItemId;
	}

	public void setLineItemId(String lineItemId) {
		this.lineItemId = lineItemId;
	}
}
