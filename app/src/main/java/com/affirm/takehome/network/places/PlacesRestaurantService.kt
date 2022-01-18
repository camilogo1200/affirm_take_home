package com.affirm.takehome.network.places

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Note: You could use this or define your own.
interface PlacesRestaurantService {

  @GET("nearbysearch/json")
  fun getPlacesRestaurants(
    @Query("location") location: String,
    @Query("pagetoken") pageToken: String,
    @Query("key") key: String = KEY,
    @Query("rankby") rankBy: String = "distance",
    @Query("type") type: String = "restaurant"): Call<PlacesResponse>

  companion object {
    internal const val KEY = "AIzaSyAcsY9zVrBham7BwJQzRNmtKfOkgtDPZsQ"
  }
}