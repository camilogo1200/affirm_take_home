package com.affirm.takehome.data.repositories.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PlacesResponse {
  @SerialName("results") val restaurants: List<PlacesRestaurant> = listOf()

  /* If there are more results, the response will contain this token which can be used to fetch them.
  If there are no more results to return, this will be empty. */
  @SerialName("next_page_token") val nextPageToken: String = ""
}
