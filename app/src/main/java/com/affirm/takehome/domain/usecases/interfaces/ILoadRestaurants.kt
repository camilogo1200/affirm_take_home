package com.affirm.takehome.domain.usecases.interfaces

import com.affirm.takehome.domain.models.Restaurant

interface ILoadRestaurants {
    suspend fun invoke(): Result<List<Restaurant>>
}
