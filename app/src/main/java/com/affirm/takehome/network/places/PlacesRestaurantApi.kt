package com.affirm.takehome.network.places

import retrofit2.Call

class PlacesRestaurantApi(private val placesRestaurantService: PlacesRestaurantService) {

  /**
   * Fetches restaurants near a given latitude and longitude. Returns up to 20 results.
   *
   * @param nextPageToken If more than 20 results exist, a previous search will contain a next page
   * token which can be included to fetch the next batch of results.
   */
  fun getRestaurants(latitude: Double, longitude: Double, nextPageToken: String = ""): Call<PlacesResponse> {
    return placesRestaurantService.getPlacesRestaurants( "$latitude,$longitude", nextPageToken)
  }
}