package com.search.nearme.model.Map_Route;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DirectionResponse {

	@SerializedName("routes")
	@Expose
	private List<RoutesItem> routes;

	@SerializedName("status")
	@Expose
	private String status;

	public void setRoutes(List<RoutesItem> routes){
		this.routes = routes;
	}

	public List<RoutesItem> getRoutes(){
		return routes;
	}


	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DirectionResponse{" +
			"routes = '" + routes + '\'' +
			",status = '" + status + '\'' + 
			"}";
		}
}