package com.affirm.takehome.ui.dashboard.viewmodels

import android.location.Location
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affirm.takehome.domain.usecases.interfaces.IAddFeedBack
import com.affirm.takehome.domain.usecases.interfaces.ILoadRestaurants
import com.affirm.takehome.ui.dashboard.view_states.DashBoardViewStates
import com.affirm.takehome.ui.dashboard.view_states.ErrorLoadingRestaurants
import com.affirm.takehome.ui.dashboard.view_states.RestaurantsLoaded
import com.affirm.takehome.utils.asLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val loadRestaurantsUC: ILoadRestaurants,
    private val addFeedBack: IAddFeedBack
) : ViewModel() {

    private val _viewState = MutableLiveData<DashBoardViewStates>()
    val viewState = _viewState.asLiveData()

    private val _positiveFeedBack = MutableLiveData<Int>()
    val positiveFeedBack = _positiveFeedBack.asLiveData()
    private val _negativeFeedBack = MutableLiveData<Int>()
    val negativeFeedBack = _negativeFeedBack.asLiveData()

    var isLoading = ObservableBoolean(false)

    private fun setViewState(viewState: DashBoardViewStates) {
        _viewState.value = viewState
    }

    fun loadRestaurants(location: Location) {
        viewModelScope.launch {
            isLoading.set(true)
            val result = loadRestaurantsUC.invoke()
            if (result.isSuccess) {
                val restaurants = result.getOrNull() ?: listOf()
                setViewState(RestaurantsLoaded(restaurants))
            } else {
                setViewState(ErrorLoadingRestaurants(result.exceptionOrNull()?.message))
            }
            isLoading.set(false)
        }
    }

    fun addFeedBack(isYesFeedback: Boolean) {
        viewModelScope.launch {
            if (isYesFeedback) {
                addFeedBack.addPositiveFeedBack()
                    .also { _positiveFeedBack.value = _positiveFeedBack.value?.plus(1) }
            } else {
                addFeedBack.addNegativeFeedBack()
                    .also { _negativeFeedBack.value = _negativeFeedBack.value?.plus(1) }
            }
        }
    }

}
