package com.affirm.takehome.network.yelp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class YelpResponse(
    @SerialName("businesses") val restaurants: List<YelpRestaurant> = listOf()
)