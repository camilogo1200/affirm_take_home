package com.affirm.takehome.domain.models

import com.affirm.takehome.data.repositories.network.api.dto.PlacesRestaurant
import com.affirm.takehome.data.repositories.network.api.dto.YelpRestaurant

data class Restaurant(
    val id: String,
    val name: String,
    val image: String,
    val rating: String
) {
    companion object {
        private const val PLACEHOLDER_IMAGE = "https://thumbs.dreamstime.com/b/print-134251864.jpg" //just a random image
        private const val PHOTO_REQUEST_PLACES =
            "https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photo_reference="
        const val KEY_PLACES = "&key=AIzaSyAcsY9zVrBham7BwJQzRNmtKfOkgtDPZsQ"

        fun fromYelp(dto: YelpRestaurant): Restaurant {
            return Restaurant(
                dto.id,
                dto.name,
                dto.image,
                dto.rating
            )
        }

        fun fromPlaces(dto: PlacesRestaurant): Restaurant {
            val photoReference = dto.photos.getOrNull(0)?.photoReference
            val restaurantPhoto = photoReference?.let {
                "$PHOTO_REQUEST_PLACES$photoReference$KEY_PLACES"
            } ?: PLACEHOLDER_IMAGE
            return Restaurant(
                dto.id,
                dto.name,
                restaurantPhoto,
                dto.rating
            )
        }
    }
}
