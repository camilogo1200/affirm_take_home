package com.affirm.takehome.domain.usecases

import com.affirm.takehome.domain.models.Restaurant
import com.affirm.takehome.domain.usecases.interfaces.ILoadPlacesRestaurants
import javax.inject.Inject

class LoadPlacesRestaurants @Inject constructor() : ILoadPlacesRestaurants {
    override suspend fun invoke(latitude: Double, longitude: Double): Result<List<Restaurant>> {
        TODO("Not yet implemented")
    }
}
