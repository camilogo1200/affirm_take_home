package com.affirm.takehome.data.repositories.datasource

import com.affirm.takehome.data.repositories.datasource.interfaces.IDataSourceSelectorRepository
import javax.inject.Inject

class DataSourceSelectorRepository @Inject constructor() : IDataSourceSelectorRepository {
    override suspend fun getNextDataSourceOrigin(): Result<String> {
        return Result.success("YELP")
    }
}
