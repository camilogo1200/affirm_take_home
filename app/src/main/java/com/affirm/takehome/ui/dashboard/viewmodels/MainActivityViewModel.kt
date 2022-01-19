package com.affirm.takehome.ui.dashboard.viewmodels

import android.location.Location
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.affirm.takehome.domain.usecases.interfaces.IAddFeedBack
import com.affirm.takehome.domain.usecases.interfaces.ILoadRestaurants
import com.affirm.takehome.ui.dashboard.view_states.DashBoardViewStates
import com.affirm.takehome.ui.dashboard.view_states.DashBoardViewStates.*
import com.affirm.takehome.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val loadRestaurantsUC: ILoadRestaurants,
    private val addFeedBack: IAddFeedBack
) : ViewModel() {

    private lateinit var lastLocation: Location
    private val _viewState = MutableLiveData<DashBoardViewStates>()
    val viewState = _viewState.asLiveData()

    private val _positiveFeedBack = MutableLiveData<Int>()
    val positiveFeedBack = _positiveFeedBack.asLiveData()
    private val _negativeFeedBack = MutableLiveData<Int>()
    val negativeFeedBack = _negativeFeedBack.asLiveData()

    fun initView() {
        _positiveFeedBack.value = 0
        _negativeFeedBack.value = 0
        val positiveCounter = positiveFeedBack.value ?: 0
        val negativeCounter = negativeFeedBack.value ?: 0
        setViewState(
            UserFeedBackAdded(
                null, positiveCounter, negativeCounter
            )
        )
    }

    val isLoading = ObservableBoolean(false)

    private fun setViewState(viewState: DashBoardViewStates) {
        _viewState.value = viewState
    }

    fun fetchMoreRestaurants() {
        loadRestaurants(lastLocation)
    }

    fun loadRestaurants(location: Location) {
        lastLocation = location
        viewModelScope.launch {
            isLoading.set(true)
            val result = loadRestaurantsUC.invoke(location.latitude, location.longitude)
            if (result.isSuccess) {
                val restaurants = result.getOrNull() ?: listOf()
                setViewState(RestaurantsLoaded(restaurants))
            } else {
                setViewState(ErrorLoadingRestaurants(result.exceptionOrNull()?.message))
            }
            isLoading.set(false)
        }
    }

    fun addFeedBack(isPositiveFeedback: Boolean) {
        viewModelScope.launch {
            if (isPositiveFeedback) {
                addFeedBack.addPositiveFeedBack()
                    .also { _positiveFeedBack.value = _positiveFeedBack.value?.plus(1) }
            } else {
                addFeedBack.addNegativeFeedBack()
                    .also { _negativeFeedBack.value = _negativeFeedBack.value?.plus(1) }
            }
            val positiveCounter = positiveFeedBack.value ?: 0
            val negativeCounter = negativeFeedBack.value ?: 0
            setViewState(
                UserFeedBackAdded(
                    isPositiveFeedback, positiveCounter, negativeCounter
                )
            )
        }
    }

}
