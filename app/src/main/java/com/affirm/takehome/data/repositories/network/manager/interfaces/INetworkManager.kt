package com.affirm.takehome.data.repositories.network.manager.interfaces

interface INetworkManager {
    suspend fun isNetworkConnected():Boolean
}
