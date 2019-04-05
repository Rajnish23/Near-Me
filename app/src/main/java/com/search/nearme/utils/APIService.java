package com.search.nearme.utils;

import com.search.nearme.model.Map_Route.DirectionResponse;
import com.search.nearme.model.Response;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("place/nearbysearch/json")
    Single<Response> searchPlaces(@Query(value = "key", encoded = true) String key,
                                                 @Query(value = "type",encoded = true) String type,
                                                 @Query(value = "location",encoded = true) String location,
                                                 @Query(value = "rankby",encoded = true)String rankby);

    @GET("/place/photo")
    Single<String> loadPlaceImage(@Query(value = "key", encoded = true) String key,
                                  @Query(value = "photoreference", encoded = true) String photoRef,
                                  @Query(value = "height",encoded = true) String height,
                                  @Query(value = "weight",encoded = true) String weight);

    @GET("directions/json")
    Single<DirectionResponse> getRoute(@Query(value = "key",encoded = true) String key,
                                       @Query(value = "origin",encoded = true) String originLatLng,
                                       @Query(value = "destination",encoded = true) String destinationLatLng,
                                       @Query(value = "mode",encoded = true) String mode);


}
