package com.affirm.takehome.domain.usecases.interfaces

interface IGetCounters {
    suspend fun getYesCounter(): Result<Int>
    suspend fun getNoCounter(): Result<Int>
}
