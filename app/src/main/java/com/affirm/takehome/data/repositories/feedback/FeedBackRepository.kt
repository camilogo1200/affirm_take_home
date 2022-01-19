package com.affirm.takehome.data.repositories.feedback

import com.affirm.takehome.data.repositories.feedback.interfaces.IFeedBackRepository
import com.affirm.takehome.data.repositories.feedback.interfaces.ILocalFeedBackRepository
import com.affirm.takehome.data.repositories.feedback.interfaces.IRemoteFeedBackRepository
import com.affirm.takehome.data.repositories.network.manager.interfaces.INetworkManager
import javax.inject.Inject

class FeedBackRepository @Inject constructor(
    private val networkManager: INetworkManager,
    private val localFeedBackRepository: ILocalFeedBackRepository,
    private val remoteFeedBackRepository: IRemoteFeedBackRepository
) : IFeedBackRepository {

    override suspend fun addPositiveFeedBack() {
        if (networkManager.isNetworkConnected()) remoteFeedBackRepository.addPositiveFeedBack()
        else localFeedBackRepository.addPositiveFeedBack()
    }

    override suspend fun addNegativeFeedBack() {
        if (networkManager.isNetworkConnected()) remoteFeedBackRepository.addNegativeFeedBack()
        else localFeedBackRepository.addNegativeFeedBack()
    }
}
