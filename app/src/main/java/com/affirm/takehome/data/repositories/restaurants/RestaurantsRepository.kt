package com.affirm.takehome.data.repositories.restaurants

import com.affirm.takehome.data.repositories.network.api.dto.PlacesRestaurant
import com.affirm.takehome.data.repositories.network.api.dto.YelpRestaurant
import com.affirm.takehome.data.repositories.network.api.services.PlacesRestaurantService
import com.affirm.takehome.data.repositories.network.api.services.YelpRestaurantService
import com.affirm.takehome.data.repositories.restaurants.interfaces.IRestaurantsRepository
import com.affirm.takehome.utils.ServiceProvider
import com.affirm.takehome.utils.coroutines.IoDispatcher
import com.affirm.takehome.utils.coroutines.networkCall
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RestaurantsRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val yelpServiceApi: YelpRestaurantService,
    private val placesServiceApi: PlacesRestaurantService
) : IRestaurantsRepository {
    private var offset = 0;
    private var pageToken = ""
    override suspend fun getYelpRestaurants(
        serviceProvider: ServiceProvider,
        latitude: Double,
        longitude: Double,
        limit: Int
    ): Result<List<YelpRestaurant>> {
        return networkCall(dispatcher) {
            val response = yelpServiceApi.getYelpRestaurants(
                latitude = latitude,
                longitude = longitude,
                limit = limit,
                offset = offset
            )
            offset = offset.plus(limit)
            if (response.isSuccessful) {
                val restaurants = response.body()?.restaurants ?: listOf()
                Result.success(restaurants)
            } else {
                Result.failure(Exception(response.errorBody().toString()))
            }
        }
    }

    override suspend fun getPlacesRestaurants(
        latitude: Double,
        longitude: Double
    ): Result<List<PlacesRestaurant>> {
        return networkCall(dispatcher) {
            val response = placesServiceApi.getPlacesRestaurants(
                location = "$latitude,$longitude",
                pageToken = pageToken
            )
            val restaurants = response.body()?.restaurants ?: listOf()
            if (response.isSuccessful) {
                pageToken = response.body()?.nextPageToken ?: ""
                Result.success(restaurants)
            } else {
                Result.failure(Exception(response.errorBody().toString()))
            }
        }
    }
}
