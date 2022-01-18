package com.affirm.takehome.ui.dashboard.activities

import android.Manifest
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.affirm.takehome.R
import com.affirm.takehome.databinding.ActivityMainBinding
import com.affirm.takehome.ui.dashboard.adapter.RestaurantAdapter
import com.affirm.takehome.ui.dashboard.view_states.DashBoardViewStates
import com.affirm.takehome.ui.dashboard.view_states.ErrorLoadingRestaurants
import com.affirm.takehome.ui.dashboard.view_states.RestaurantsLoaded
import com.affirm.takehome.ui.dashboard.viewmodels.MainActivityViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates.observable

private const val LOCATION_PERMISSION_CODE = 101
private const val THUMB_UP = R.drawable.thumb_up
private const val THUMB_DOWN = R.drawable.thumb_down
private val TAG = MainActivity::class.simpleName

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    private var animating = false

    private val restaurantAdapter by lazy {
        RestaurantAdapter()
    }

    private var yesCounter: Int by observable(0) { _, _, newValue ->
        binding.yesCounterText.text = newValue.toString()
    }

    private var noCounter: Int by observable(0) { _, _, newValue ->
        binding.noCounterText.text = newValue.toString()
    }

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bindObservables()
        initView()
        checkAndRequestPermissionsForLocation()
    }

    private fun bindObservables() {
        viewModel.viewState.observe(this, ::handleViewState)
    }

    private fun handleViewState(dashBoardViewStates: DashBoardViewStates?) {
        dashBoardViewStates?.let { viewState ->
            when (viewState) {
                is RestaurantsLoaded -> handleRestaurantsLoaded()
                is ErrorLoadingRestaurants -> handleErrorLoadingRestaurants()
            }
        }
    }

    private fun handleErrorLoadingRestaurants() {

    }

    private fun handleRestaurantsLoaded() {

    }

    private fun initView() {
        with(binding) {
            viewPager.adapter = restaurantAdapter
            // Only allow button input, swiping not allowed
            viewPager.isUserInputEnabled = false

            yesButton.setOnClickListener {
                // Make sure the previous animation finishes
                if (!animating) {
                    viewPager.currentItem = viewPager.currentItem + 1
                    animateIcon(THUMB_UP)
                }
            }

            noButton.setOnClickListener {
                if (!animating) {
                    viewPager.currentItem = viewPager.currentItem + 1
                    animateIcon(THUMB_DOWN)
                }
            }

            yesCounterText.text = yesCounter.toString()
            noCounterText.text = noCounter.toString()
        }
    }

    private fun animateIcon(drawable: Int) {
        animating = true
        binding.icon.setImageDrawable(ContextCompat.getDrawable(this, drawable))
        binding.icon.alpha = 0.5f
        binding.icon.visibility = View.VISIBLE
        binding.icon.animate()
            .alpha(1f)
            .setDuration(300)
            .scaleX(2f)
            .scaleY(2f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.icon.visibility = View.GONE
                    animating = false
                }
            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
            ) {
                loadLocation()
            } else {
                Toast.makeText(this, getString(R.string.no_permission), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkAndRequestPermissionsForLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_CODE
            )
        } else {
            loadLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun loadLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location == null) {
                // request the location
                fusedLocationProviderClient.requestLocationUpdates(
                    LocationRequest.create(),
                    object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            super.onLocationResult(locationResult)

                            locationResult.locations.lastOrNull().let { location ->
                                if (location == null) {
                                    Log.d(TAG, "Location load fail")
                                    false
                                } else {
                                    // TODO: load restaurants using "location"
                                    true
                                }
                            }
                            fusedLocationProviderClient.removeLocationUpdates(this)
                        }
                    },
                    Looper.getMainLooper()
                )
            } else {
                // TODO: load restaurants using "location"
                viewModel.loadRestaurants(location)
            }
        }
    }

}
