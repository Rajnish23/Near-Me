package com.search.nearme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{


	@SerializedName("results")
	@Expose
	private List<SearchResultsItem> results;

	@SerializedName("status")
	@Expose
	private String status;


	public void setResults(List<SearchResultsItem> results){
		this.results = results;
	}

	public List<SearchResultsItem> getResults(){
		return results;
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
			",results = '" + results + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}