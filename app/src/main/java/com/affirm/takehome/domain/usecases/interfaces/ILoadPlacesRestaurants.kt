package com.affirm.takehome.domain.usecases.interfaces

import com.affirm.takehome.domain.models.Restaurant

interface ILoadPlacesRestaurants {
    suspend fun invoke(latitude: Double, longitude: Double): Result<List<Restaurant>>
}
