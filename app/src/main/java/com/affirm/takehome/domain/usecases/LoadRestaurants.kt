package com.affirm.takehome.domain.usecases

import com.affirm.takehome.data.repositories.datasource.interfaces.IDataSourceSelectorRepository
import com.affirm.takehome.domain.models.Restaurant
import com.affirm.takehome.domain.usecases.interfaces.ILoadPlacesRestaurants
import com.affirm.takehome.domain.usecases.interfaces.ILoadRestaurants
import com.affirm.takehome.domain.usecases.interfaces.ILoadYelpRestaurants
import javax.inject.Inject

class LoadRestaurants @Inject constructor(
    private val dataSourceSelectorRepository: IDataSourceSelectorRepository,
    private val loadYelpRestaurantsUC: ILoadYelpRestaurants,
    private val loadPlacesRestaurantsUC: ILoadPlacesRestaurants,
) : ILoadRestaurants {

    companion object {
        private const val YELP_DATASOURCE_NAME = "YELP"
    }

    override suspend fun invoke(): Result<List<Restaurant>> {
        return if (isYelpDataSource()) {
            loadYelpRestaurantsUC.invoke()
        } else loadPlacesRestaurantsUC.invoke()
    }

    private suspend fun isYelpDataSource(): Boolean {
        val datasource = dataSourceSelectorRepository.getNextDataSourceOrigin()
        if (datasource.isSuccess) {
            return datasource.equals(YELP_DATASOURCE_NAME)
        }
        return false
    }
}
