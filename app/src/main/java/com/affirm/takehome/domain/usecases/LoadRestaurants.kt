package com.affirm.takehome.domain.usecases

import com.affirm.takehome.data.repositories.datasource.interfaces.IDataSourceSelectorRepository
import com.affirm.takehome.domain.models.Restaurant
import com.affirm.takehome.domain.usecases.interfaces.ILoadPlacesRestaurants
import com.affirm.takehome.domain.usecases.interfaces.ILoadRestaurants
import com.affirm.takehome.domain.usecases.interfaces.ILoadYelpRestaurants
import com.affirm.takehome.utils.ServiceProvider
import javax.inject.Inject

class LoadRestaurants @Inject constructor(
    private val dataSourceSelectorRepository: IDataSourceSelectorRepository,
    private val loadYelpRestaurantsUC: ILoadYelpRestaurants,
    private val loadPlacesRestaurantsUC: ILoadPlacesRestaurants,
) : ILoadRestaurants {

    companion object {
        private const val YELP_DATASOURCE_NAME = "YELP"
    }

    override suspend fun invoke(latitude: Double, longitude: Double): Result<List<Restaurant>> {
        return when (getDataSource()) {
            ServiceProvider.YELP -> loadYelpRestaurantsUC.invoke(latitude, longitude)
            ServiceProvider.PLACES -> loadPlacesRestaurantsUC.invoke(latitude, longitude)
        }
    }

    private suspend fun getDataSource(): ServiceProvider {
        val datasource = dataSourceSelectorRepository.getNextDataSourceOrigin()
        return if (datasource.isSuccess) {
            return datasource.getOrNull() ?: ServiceProvider.YELP
        } else ServiceProvider.YELP
    }
}
