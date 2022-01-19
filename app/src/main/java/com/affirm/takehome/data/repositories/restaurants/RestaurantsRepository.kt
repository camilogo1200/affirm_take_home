package com.affirm.takehome.data.repositories.restaurants

import com.affirm.takehome.data.repositories.network.api.dto.YelpRestaurant
import com.affirm.takehome.data.repositories.network.api.services.YelpRestaurantService
import com.affirm.takehome.data.repositories.restaurants.interfaces.IRestaurantsRepository
import com.affirm.takehome.utils.ServiceProvider
import com.affirm.takehome.utils.coroutines.IoDispatcher
import com.affirm.takehome.utils.coroutines.networkCall
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RestaurantsRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val yelpServiceApi: YelpRestaurantService
) : IRestaurantsRepository {
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
                limit = limit
            )
            if (response.isSuccessful) {
                val restaurants = response.body()?.restaurants ?: listOf()
                Result.success(restaurants)
            } else {
                Result.failure(Exception(response.errorBody().toString()))
            }
        }
    }
}
