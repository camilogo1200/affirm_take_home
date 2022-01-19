package com.affirm.takehome.ui.dashboard.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.affirm.takehome.R
import com.affirm.takehome.databinding.ActivityMainBinding
import com.affirm.takehome.domain.models.Restaurant
import com.affirm.takehome.ui.dashboard.adapter.RestaurantAdapter
import com.affirm.takehome.ui.dashboard.view_states.DashBoardViewStates
import com.affirm.takehome.ui.dashboard.view_states.DashBoardViewStates.*
import com.affirm.takehome.ui.dashboard.viewmodels.MainActivityViewModel
import com.affirm.takehome.utils.RequestPermissionHelper
import dagger.hilt.android.AndroidEntryPoint


private const val THUMB_UP = R.drawable.thumb_up
private const val THUMB_DOWN = R.drawable.thumb_down
private val TAG = MainActivity::class.simpleName

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var permissionHelper: RequestPermissionHelper
    private var animating = false
    private val restaurantAdapter by lazy { RestaurantAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        permissionHelper = RequestPermissionHelper(this, viewModel)
        bindObservables()
        initView()
        permissionHelper.checkAndRequestPermissionsForLocation()
    }

    private fun bindObservables() {
        viewModel.viewState.observe(this, ::handleViewState)
    }

    private fun handleFetchMoreRestaurants(position: Int?) {
        viewModel.fetchMoreRestaurants()
    }

    private fun initView() {
        binding.viewModel = viewModel
        binding.viewPager.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    if (state == ViewPager2.SCROLL_STATE_IDLE || state == ViewPager2.SCROLL_STATE_DRAGGING) {
                        if (currentItem == adapter?.itemCount?.minus(1)) {
                            viewModel.fetchMoreRestaurants()
                        }
                    }
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })

            adapter = restaurantAdapter
            viewModel.initView()
        }
    }

    private fun handleViewState(dashBoardViewStates: DashBoardViewStates?) {
        dashBoardViewStates?.let { viewState ->
            when (viewState) {
                is RestaurantsLoaded -> handleRestaurantsLoaded(viewState.restaurants)
                is ErrorLoadingRestaurants -> handleErrorLoadingRestaurants()
                is UserFeedBackAdded -> handleFeedBackAdded(
                    viewState.isPositiveFeedBack,
                    viewState.positiveCounter,
                    viewState.negativeCounter
                )
            }
        }
    }

    private fun handleFeedBackAdded(
        isPositiveFeedBack: Boolean?,
        positiveCounter: Int,
        negativeCounter: Int
    ) {
        isPositiveFeedBack?.let {
            val icon = if (isPositiveFeedBack) THUMB_UP else THUMB_DOWN
            if (!animating) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1, true)
                    .also { animateIcon(icon) }
            }
        }
        binding.yesCounterText.text = positiveCounter.toString()
        binding.noCounterText.text = negativeCounter.toString()
    }

    private fun handleErrorLoadingRestaurants() {
        TODO("Show error loading restaurants")
    }

    private fun handleRestaurantsLoaded(restaurants: List<Restaurant>) {
        restaurantAdapter.addRestaurants(restaurants)
    }

    private fun animateIcon(drawable: Int) {
        animating = true
        val drawableRes = ContextCompat.getDrawable(this, drawable)
        binding.icon.apply {
            setImageDrawable(drawableRes)
            alpha = 0.5f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(300)
                .scaleX(2f)
                .scaleY(2f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        visibility = View.GONE
                        animating = false
                    }
                })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
