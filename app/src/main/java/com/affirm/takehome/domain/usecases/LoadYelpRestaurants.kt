package com.affirm.takehome.domain.usecases

import com.affirm.takehome.domain.models.Restaurant
import com.affirm.takehome.domain.usecases.interfaces.ILoadYelpRestaurants

class LoadYelpRestaurants : ILoadYelpRestaurants {
    override suspend fun invoke(): Result<List<Restaurant>> {
        TODO("Not yet implemented")
    }
}
