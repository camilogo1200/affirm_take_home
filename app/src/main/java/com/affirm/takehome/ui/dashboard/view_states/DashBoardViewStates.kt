package com.affirm.takehome.ui.dashboard.view_states

import com.affirm.takehome.domain.models.Restaurant

sealed interface DashBoardViewStates {

    data class RestaurantsLoaded(
        val restaurants: List<Restaurant>
    ) : DashBoardViewStates

    data class ErrorLoadingRestaurants(val message: String?) : DashBoardViewStates

    data class UserFeedBackAdded(
        val isPositiveFeedBack: Boolean?,
        val positiveCounter: Int,
        val negativeCounter: Int
    ) : DashBoardViewStates
}
