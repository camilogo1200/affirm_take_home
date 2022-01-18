package com.affirm.takehome.network.places

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private const val PLACES_API_BASE_URL = "https://maps.googleapis.com/maps/api/place/"

class PlacesRestaurantApiFactory {

  companion object {

    fun create(): PlacesRestaurantApi {
      val logging = HttpLoggingInterceptor()
      logging.setLevel(HttpLoggingInterceptor.Level.BODY)
      val httpClient = OkHttpClient.Builder()
      httpClient.addInterceptor(logging)

      val contentType = "application/json".toMediaType()
      val factory = Json {
        isLenient = true
        ignoreUnknownKeys = true
      }.asConverterFactory(contentType)

      val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(factory)
        .baseUrl(PLACES_API_BASE_URL)
        .client(httpClient.build())
        .build()

      return PlacesRestaurantApi(retrofit.create(PlacesRestaurantService::class.java))
    }
  }
}