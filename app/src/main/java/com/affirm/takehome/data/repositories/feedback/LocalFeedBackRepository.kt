package com.affirm.takehome.data.repositories.feedback

import com.affirm.takehome.data.repositories.feedback.interfaces.ILocalFeedBackRepository
import javax.inject.Inject

class LocalFeedBackRepository @Inject constructor() : ILocalFeedBackRepository {
    override suspend fun addPositiveFeedBack() {
        TODO("Insert using room")
    }

    override suspend fun addNegativeFeedBack() {
        TODO("Insert using room")
    }
}
