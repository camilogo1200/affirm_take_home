package com.affirm.takehome.data.di.modules

import com.affirm.takehome.utils.network.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECTION_TIMEOUT = 10L
    private const val READ_TIMEOUT = 2L


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

}
