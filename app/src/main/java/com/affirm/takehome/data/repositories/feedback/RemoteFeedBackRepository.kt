package com.affirm.takehome.data.repositories.feedback

import com.affirm.takehome.data.repositories.feedback.interfaces.IRemoteFeedBackRepository
import com.affirm.takehome.data.repositories.network.api.services.PlacesRestaurantService
import com.affirm.takehome.data.repositories.network.api.services.YelpRestaurantService
import com.affirm.takehome.utils.coroutines.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoteFeedBackRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val yelpService: YelpRestaurantService,
    private val placesService: PlacesRestaurantService
) : IRemoteFeedBackRepository {
    override suspend fun addPositiveFeedBack() {
        TODO("provide feedback using services")
    }


    override suspend fun addNegativeFeedBack() {
        TODO("provide feedback using services")
    }
}
