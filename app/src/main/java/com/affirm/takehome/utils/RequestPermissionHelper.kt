package com.affirm.takehome.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.affirm.takehome.R
import com.affirm.takehome.ui.dashboard.viewmodels.MainActivityViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices


private val TAG = RequestPermissionHelper::class.simpleName

class RequestPermissionHelper(
    private val context: Activity,
    private val viewModel: MainActivityViewModel
) :
    ActivityCompat.OnRequestPermissionsResultCallback {
    private val LOCATION_PERMISSION_CODE = 101
    private val mockLocation = true

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    fun checkAndRequestPermissionsForLocation() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_CODE
            )
        } else {
            loadLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        context.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
            ) {
                loadLocation()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_permission),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun loadLocation() {
        if (mockLocation) setMockLocation()
        else requestRealLocation()

    }


    @SuppressLint("MissingPermission")
    private fun requestRealLocation() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            if (location == null) {
                // request the location
                fusedLocationProviderClient.requestLocationUpdates(
                    LocationRequest.create(), locationHandler, Looper.getMainLooper()
                )
            } else {
                viewModel.loadRestaurants(location)
            }
        }
    }

    private val locationHandler = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            locationResult.locations.lastOrNull().let { location ->
                if (location == null) {
                    Log.d(TAG, "Location load fail")
                    false
                } else {
                    viewModel.loadRestaurants(location)
                    true
                }
            }
            fusedLocationProviderClient.removeLocationUpdates(this)
        }
    }

    @SuppressLint("MissingPermission")
    private fun setMockLocation() {
        fusedLocationProviderClient.setMockMode(true)
        val location = arrayOf("40.783058,-73.971252")
        val tokens: List<String> = location[0].split(",")
        val newLocation = Location(LocationManager.GPS_PROVIDER)
        newLocation.latitude = tokens[0].toDouble()
        newLocation.longitude = tokens[1].toDouble()
        newLocation.accuracy = 3.0f
        newLocation.time = System.currentTimeMillis()
        fusedLocationProviderClient.setMockLocation(newLocation)
            .addOnSuccessListener {
                requestRealLocation()
            }.addOnFailureListener {
                Log.d(
                    TAG,
                    it.message
                        ?: "Error mocking location. Check developer settings -> mock location on device"
                )
            }
    }
}
