package com.affirm.takehome.domain.usecases.interfaces

import com.affirm.takehome.domain.models.Restaurant

interface ILoadPlacesRestaurants {
    suspend fun invoke(): Result<List<Restaurant>>
}
