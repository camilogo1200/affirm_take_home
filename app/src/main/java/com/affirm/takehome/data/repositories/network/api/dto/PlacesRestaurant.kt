package com.affirm.takehome.data.repositories.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlacesRestaurant(
  @SerialName("place_id") val id: String,
  @SerialName("name") val name: String,
  @SerialName("photos") val photos: List<RestaurantPhoto> = emptyList(),
  @SerialName("rating") val rating: String = ""
)

@Serializable
data class RestaurantPhoto(
  @SerialName("photo_reference") val photoReference: String,
  val height: Int,
  val width: Int
)
