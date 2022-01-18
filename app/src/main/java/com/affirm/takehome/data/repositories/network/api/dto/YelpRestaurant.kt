package com.affirm.takehome.data.repositories.network.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YelpRestaurant(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("image_url") val image: String,
    @SerialName("rating") val rating: String
)
