package com.youtube.dashboard.config

import com.youtube.dashboard.services.VideoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DashboardCustomRetrofit {


//   For Emulator --> private val BASE_URL = "http://10.0.2.2:9050/api/"


    private val BASE_URL = "http://192.168.1.219:9050/api/"


    @Singleton
    @Provides
    @Named("dashboardRetrofit")
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun providesVideoService(@Named("dashboardRetrofit") retrofit: Retrofit): VideoService {
        return retrofit.create(VideoService::class.java)
    }
}