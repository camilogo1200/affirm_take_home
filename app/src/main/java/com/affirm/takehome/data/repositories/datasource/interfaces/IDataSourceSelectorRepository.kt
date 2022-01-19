package com.affirm.takehome.data.repositories.datasource.interfaces

import com.affirm.takehome.utils.ServiceProvider

interface IDataSourceSelectorRepository {
    suspend fun getNextDataSourceOrigin(): Result<ServiceProvider>
}
