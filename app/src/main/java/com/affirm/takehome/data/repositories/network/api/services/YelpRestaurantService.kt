package com.affirm.takehome.data.repositories.network.api.services

import com.affirm.takehome.data.repositories.network.api.dto.YelpResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// Note: You could use this or define your own.
interface YelpRestaurantService {
    @GET("v3/businesses/search")
    suspend fun getYelpRestaurants(
        @Header(value = "Authorization") token: String = TOKEN,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("offset") offset: Int =0,
        @Query("limit") limit: Int = 50
    ): Response<YelpResponse>

    companion object {
        private const val TOKEN =
            "Bearer itoMaM6DJBtqD54BHSZQY9WdWR5xI_CnpZdxa3SG5i7N0M37VK1HklDDF4ifYh8SI-P2kI_mRj5KRSF4_FhTUAkEw322L8L8RY6bF1UB8jFx3TOR0-wW6Tk0KftNXXYx"
    }
}
