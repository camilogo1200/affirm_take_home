package com.affirm.takehome.data.repositories.network.manager.interfaces

class NetworkManager : INetworkManager {
    override suspend fun isNetworkConnected(): Boolean {
        return false //TODO implement properly with lifecycle aware listener -> scope of project is only local feedback
    }
}
