package com.affirm.takehome.data.di.modules

import com.affirm.takehome.data.repositories.datasource.DataSourceSelectorRepository
import com.affirm.takehome.data.repositories.datasource.interfaces.IDataSourceSelectorRepository
import com.affirm.takehome.data.repositories.feedback.FeedBackRepository
import com.affirm.takehome.data.repositories.feedback.LocalFeedBackRepository
import com.affirm.takehome.data.repositories.feedback.RemoteFeedBackRepository
import com.affirm.takehome.data.repositories.feedback.interfaces.IFeedBackRepository
import com.affirm.takehome.data.repositories.feedback.interfaces.ILocalFeedBackRepository
import com.affirm.takehome.data.repositories.feedback.interfaces.IRemoteFeedBackRepository
import com.affirm.takehome.data.repositories.restaurants.RestaurantsRepository
import com.affirm.takehome.data.repositories.restaurants.interfaces.IRestaurantsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsDataSourceSelectorRepository(repository: DataSourceSelectorRepository): IDataSourceSelectorRepository

    @Binds
    abstract fun bindsFeedBackRepository(repository: FeedBackRepository): IFeedBackRepository

    @Binds
    abstract fun bindsLocalFeedBackRepository(repository: LocalFeedBackRepository): ILocalFeedBackRepository

    @Binds
    abstract fun bindsRemoteFeedBackRepository(repository: RemoteFeedBackRepository): IRemoteFeedBackRepository

    @Binds
    abstract fun bindRestaurantsRepository(repository: RestaurantsRepository): IRestaurantsRepository
}
