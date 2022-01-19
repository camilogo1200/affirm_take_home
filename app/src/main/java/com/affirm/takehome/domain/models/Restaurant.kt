package com.affirm.takehome.domain.models

import com.affirm.takehome.data.repositories.network.api.dto.YelpRestaurant

data class Restaurant(
    val id: String,
    val name: String,
    val image: String,
    val rating: String
) {
    companion object {
        fun fromYelp(dto: YelpRestaurant): Restaurant {
            return Restaurant(
                dto.id,
                dto.name,
                dto.image,
                dto.rating
            )
        }
    }
}
