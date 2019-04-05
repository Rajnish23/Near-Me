package com.search.nearme.model.Map_Route;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OverviewPolyline{

	@SerializedName("points")
	@Expose
	private String points;

	public void setPoints(String points){
		this.points = points;
	}

	public String getPoints(){
		return points;
	}

	@Override
 	public String toString(){
		return 
			"OverviewPolyline{" + 
			"points = '" + points + '\'' + 
			"}";
		}
}
