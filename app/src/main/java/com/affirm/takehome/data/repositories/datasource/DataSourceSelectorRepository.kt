package com.affirm.takehome.data.repositories.datasource

import com.affirm.takehome.data.repositories.datasource.interfaces.IDataSourceSelectorRepository

class DataSourceSelectorRepository : IDataSourceSelectorRepository {
    override suspend fun getNextDataSourceOrigin(): Result<String> {
        return Result.success("YELP")
    }
}
