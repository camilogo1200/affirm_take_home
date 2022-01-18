package com.affirm.takehome.data.di.modules

import com.affirm.takehome.data.repositories.datasource.DataSourceSelectorRepository
import com.affirm.takehome.data.repositories.datasource.interfaces.IDataSourceSelectorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsDataSourceSelectorRepository(repository: DataSourceSelectorRepository): IDataSourceSelectorRepository

}
