package com.affirm.takehome.data.repositories.network.api.services

import com.affirm.takehome.data.repositories.network.api.dto.PlacesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Note: You could use this or define your own.
interface PlacesRestaurantService {

    @GET("nearbysearch/json")
    suspend fun getPlacesRestaurants(
        @Query("location") location: String,
        @Query("pagetoken") pageToken: String,
        @Query("key") key: String = KEY,
        @Query("rankby") rankBy: String = "distance",
        @Query("type") type: String = "restaurant"
    ): Response<PlacesResponse>

    companion object {
        internal const val KEY = "AIzaSyAcsY9zVrBham7BwJQzRNmtKfOkgtDPZsQ"
    }
}
