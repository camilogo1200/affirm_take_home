package com.affirm.takehome.data.repositories.feedback

import android.util.Log
import com.affirm.takehome.data.repositories.feedback.interfaces.ILocalFeedBackRepository
import javax.inject.Inject

class LocalFeedBackRepository @Inject constructor(

) : ILocalFeedBackRepository {
    override suspend fun addPositiveFeedBack() {
        // TODO("Insert using room")
        Log.d(LocalFeedBackRepository::class.simpleName, "positive feedback added")
    }

    override suspend fun addNegativeFeedBack() {
        // TODO("Insert using room")
        Log.d(LocalFeedBackRepository::class.simpleName, "negative feedback added")
    }
}
