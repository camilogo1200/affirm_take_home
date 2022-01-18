package com.affirm.takehome.data.repositories.feedback.interfaces

interface IFeedBackRepository {
    suspend fun addPositiveFeedBack()
    suspend fun addNegativeFeedBack()
}
