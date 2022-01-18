package com.affirm.takehome.data.repositories.datasource.interfaces

interface IDataSourceSelectorRepository {
    suspend fun getNextDataSourceOrigin(): Result<String>
}
