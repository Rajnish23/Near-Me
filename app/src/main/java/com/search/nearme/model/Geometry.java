package com.search.nearme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry{


	@SerializedName("location")
	@Expose
	private Location location;


	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	@Override
 	public String toString(){
		return 
			"Geometry{" +
			",location = '" + location + '\'' + 
			"}";
		}
}
