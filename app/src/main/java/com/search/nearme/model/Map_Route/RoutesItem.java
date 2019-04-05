package com.search.nearme.model.Map_Route;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoutesItem{


	@SerializedName("overview_polyline")
	@Expose
	private OverviewPolyline overviewPolyline;


	public void setOverviewPolyline(OverviewPolyline overviewPolyline){
		this.overviewPolyline = overviewPolyline;
	}

	public OverviewPolyline getOverviewPolyline(){
		return overviewPolyline;
	}


	@Override
 	public String toString(){
		return 
			"RoutesItem{" +
			",overview_polyline = '" + overviewPolyline + '\'' +
			"}";
		}
}