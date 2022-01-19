package com.affirm.takehome.data.repositories.datasource

import com.affirm.takehome.data.repositories.datasource.interfaces.IDataSourceSelectorRepository
import com.affirm.takehome.utils.ServiceProvider
import javax.inject.Inject

class DataSourceSelectorRepository @Inject constructor() : IDataSourceSelectorRepository {
    override suspend fun getNextDataSourceOrigin(): Result<ServiceProvider> {
        return Result.success(ServiceProvider.YELP)
    }
}
