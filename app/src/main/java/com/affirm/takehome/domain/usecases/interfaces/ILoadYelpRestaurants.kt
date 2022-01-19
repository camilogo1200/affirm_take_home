package com.affirm.takehome.domain.usecases.interfaces

import com.affirm.takehome.domain.models.Restaurant

interface ILoadYelpRestaurants {
    suspend fun invoke(latitude: Double, longitude: Double): Result<List<Restaurant>>
}
