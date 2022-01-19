package com.affirm.takehome.domain.usecases

import com.affirm.takehome.data.repositories.restaurants.interfaces.IRestaurantsRepository
import com.affirm.takehome.domain.models.Restaurant
import com.affirm.takehome.domain.usecases.interfaces.ILoadPlacesRestaurants
import javax.inject.Inject

class LoadPlacesRestaurants @Inject constructor(
    private val restaurantsRepository: IRestaurantsRepository
) : ILoadPlacesRestaurants {
    override suspend fun invoke(latitude: Double, longitude: Double): Result<List<Restaurant>> {
        val result = restaurantsRepository.getPlacesRestaurants(latitude, longitude)
        return if (result.isSuccess) {
            val restaurants = result.getOrNull() ?: listOf()
            Result.success(restaurants.map { Restaurant.fromPlaces(it) })
        } else {
            Result.failure(
                result.exceptionOrNull() ?: Exception("Error loading restaurants from places API")
            )
        }
    }
}
