package com.affirm.takehome.domain.usecases

import com.affirm.takehome.data.repositories.feedback.interfaces.IFeedBackRepository
import com.affirm.takehome.domain.usecases.interfaces.IAddFeedBack
import javax.inject.Inject

class AddFeedBack @Inject constructor(
    private val feedBackRepository: IFeedBackRepository
) : IAddFeedBack {
    override suspend fun addPositiveFeedBack() {
        return feedBackRepository.addPositiveFeedBack()
    }

    override suspend fun addNegativeFeedBack() {
        return feedBackRepository.addNegativeFeedBack()
    }
}
