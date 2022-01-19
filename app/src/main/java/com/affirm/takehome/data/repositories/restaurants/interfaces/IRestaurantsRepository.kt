package com.affirm.takehome.data.repositories.restaurants.interfaces

import com.affirm.takehome.data.repositories.network.api.dto.YelpRestaurant
import com.affirm.takehome.utils.ServiceProvider

interface IRestaurantsRepository {
    suspend fun getYelpRestaurants(
        serviceProvider: ServiceProvider,
        latitude: Double,
        longitude: Double,
        limit: Int
    ): Result<List<YelpRestaurant>>
}
