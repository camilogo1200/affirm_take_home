package com.affirm.takehome.domain.di.modules

import com.affirm.takehome.domain.usecases.AddFeedBack
import com.affirm.takehome.domain.usecases.LoadPlacesRestaurants
import com.affirm.takehome.domain.usecases.LoadRestaurants
import com.affirm.takehome.domain.usecases.LoadYelpRestaurants
import com.affirm.takehome.domain.usecases.interfaces.IAddFeedBack
import com.affirm.takehome.domain.usecases.interfaces.ILoadPlacesRestaurants
import com.affirm.takehome.domain.usecases.interfaces.ILoadRestaurants
import com.affirm.takehome.domain.usecases.interfaces.ILoadYelpRestaurants
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCasesModule {

    @Binds
    abstract fun bindsILoadRestaurantsUseCase(useCase: LoadRestaurants): ILoadRestaurants

    @Binds
    abstract fun bindLoadPlacesRestaurantsUseCase(useCase: LoadPlacesRestaurants): ILoadPlacesRestaurants

    @Binds
    abstract fun bindLoadYelpRestaurantsUseCase(useCase: LoadYelpRestaurants): ILoadYelpRestaurants

    @Binds
    abstract fun bindAddFeedBackUseCase(useCase: AddFeedBack): IAddFeedBack

}
