package com.search.nearme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResultsItem {

	@SerializedName("geometry")
	@Expose
	private Geometry geometry;

	@SerializedName("id")
	@Expose
	private String id;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("opening_hours")
	@Expose
	private OpeningHours openingHours;

	@SerializedName("place_id")
	@Expose
	private String placeId;

	@SerializedName("rating")
	@Expose
	private double rating;

	@SerializedName("user_ratings_total")
	@Expose
	private int userRatingsTotal;

	@SerializedName("vicinity")
	@Expose
	private String vicinity;



	public void setRating(double rating){
		this.rating = rating;
	}

	public double getRating(){
		return rating;
	}

	public void setUserRatingsTotal(int userRatingsTotal){
		this.userRatingsTotal = userRatingsTotal;
	}

	public int getUserRatingsTotal(){
		return userRatingsTotal;
	}


	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public void setVicinity(String vicinity){
		this.vicinity = vicinity;
	}

	public String getVicinity(){
		return vicinity;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}


	public void setPlaceId(String placeId){
		this.placeId = placeId;
	}

	public String getPlaceId(){
		return placeId;
	}

	public void setOpeningHours(OpeningHours openingHours){
		this.openingHours = openingHours;
	}

	public OpeningHours getOpeningHours(){
		return openingHours;
	}
	@Override
 	public String toString(){
		return 
			"SearchResultsItem{" +
			",rating = '" + rating + '\'' +
			",user_ratings_total = '" + userRatingsTotal + '\'' +
			",name = '" + name + '\'' + 
			",geometry = '" + geometry + '\'' + 
			",vicinity = '" + vicinity + '\'' + 
			",id = '" + id + '\'' +
			",place_id = '" + placeId + '\'' + 
			",opening_hours = '" + openingHours + '\'' +
			"}";
		}
}