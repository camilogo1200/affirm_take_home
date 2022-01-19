package com.affirm.takehome.data.di.modules

import com.affirm.takehome.data.repositories.network.api.services.PlacesRestaurantService
import com.affirm.takehome.data.repositories.network.api.services.YelpRestaurantService
import com.affirm.takehome.data.repositories.network.manager.interfaces.INetworkManager
import com.affirm.takehome.data.repositories.network.manager.interfaces.NetworkManager
import com.affirm.takehome.utils.network.LoggingInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val APPLICATION_JSON_MEDIA_TYPE = "application/json"
    private const val YELP_API_BASE_URL = "https://api.yelp.com/"
    private const val PLACES_API_BASE_URL = "https://maps.googleapis.com/maps/api/place/"
    private const val CONNECTION_TIMEOUT = 10L
    private const val READ_TIMEOUT = 2L


    @Provides
    fun providesPlacesService(retrofitBuilder: Retrofit.Builder): PlacesRestaurantService {
        val retrofit = retrofitBuilder
            .baseUrl(PLACES_API_BASE_URL)
            .build()
        return retrofit.create(PlacesRestaurantService::class.java)
    }

    @Provides
    fun providesYelpService(retrofitBuilder: Retrofit.Builder): YelpRestaurantService {
        val retrofit = retrofitBuilder
            .baseUrl(YELP_API_BASE_URL)
            .build()
        return retrofit.create(YelpRestaurantService::class.java)
    }


    @Provides
    fun providesRetrofitClient(
        okHttpclient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(okHttpclient)
    }

    private val jsonProperties = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    @Provides
    fun providesKotlinxConverterFactory(): Converter.Factory {
        val contentType = APPLICATION_JSON_MEDIA_TYPE.toMediaType()
        return jsonProperties.asConverterFactory(contentType)
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        builder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        builder.readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        return builder.build()
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        return LoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideNetworkManager(): INetworkManager {
        return NetworkManager()
    }

}
