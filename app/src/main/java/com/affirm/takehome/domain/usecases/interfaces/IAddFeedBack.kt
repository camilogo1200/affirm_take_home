package com.affirm.takehome.domain.usecases.interfaces

interface IAddFeedBack {
    suspend fun addPositiveFeedBack()
    suspend fun addNegativeFeedBack()
}
