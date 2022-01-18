package com.affirm.takehome.domain.usecases

import com.affirm.takehome.domain.usecases.interfaces.IGetCounters

class GetCounters : IGetCounters {
    override suspend fun getYesCounter(): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getNoCounter(): Result<Int> {
        TODO("Not yet implemented")
    }
}
