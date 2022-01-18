package com.affirm.takehome.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext

suspend fun <T> networkCall(
    coroutineDispatcher: CoroutineDispatcher,
    block: suspend () -> Result<T>
): Result<T> {
    return withContext(coroutineDispatcher) {
        ensureActive()
        try {
            block.invoke()
        } catch (ex: Exception) {
            val msg = ex.localizedMessage
            return@withContext Result.failure<T>(ex)
        }
    }
}
