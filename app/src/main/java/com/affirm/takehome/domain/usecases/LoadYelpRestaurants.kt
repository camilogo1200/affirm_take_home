package com.affirm.takehome.domain.usecases

import com.affirm.takehome.data.repositories.restaurants.interfaces.IRestaurantsRepository
import com.affirm.takehome.domain.models.Restaurant
import com.affirm.takehome.domain.usecases.interfaces.ILoadYelpRestaurants
import com.affirm.takehome.utils.ServiceProvider
import javax.inject.Inject

class LoadYelpRestaurants @Inject constructor(
    private val restaurantsRepository: IRestaurantsRepository
) : ILoadYelpRestaurants {
    override suspend fun invoke(latitude: Double, longitude: Double): Result<List<Restaurant>> {
        val limit = 20 // limit of results -> this should be stored on app settings repo
        val result =
            restaurantsRepository.getYelpRestaurants(
                ServiceProvider.YELP,
                latitude,
                longitude,
                limit
            )
        return if (result.isSuccess) {
            val restaurants = result.getOrNull() ?: listOf()
            Result.success(restaurants.map { Restaurant.fromYelp(it) })
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Cannot load Yelp restaurants"))
        }
    }
}
