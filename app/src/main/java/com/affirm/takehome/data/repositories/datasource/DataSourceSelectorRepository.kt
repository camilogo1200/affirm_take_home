package com.affirm.takehome.data.repositories.datasource

import com.affirm.takehome.data.repositories.datasource.interfaces.IDataSourceSelectorRepository
import com.affirm.takehome.utils.ServiceProvider
import javax.inject.Inject

class DataSourceSelectorRepository @Inject constructor() : IDataSourceSelectorRepository {
    private var toggleDataSource: Boolean = false
    override suspend fun getNextDataSourceOrigin(): Result<ServiceProvider> {
        toggleDataSource = !toggleDataSource //TODO implement proper logic
        return Result.success(
            if (toggleDataSource) {
                ServiceProvider.YELP
            } else {
                ServiceProvider.PLACES
            }
        )
    }
}
