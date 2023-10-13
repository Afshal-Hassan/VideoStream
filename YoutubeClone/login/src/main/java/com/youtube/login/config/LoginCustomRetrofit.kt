package com.youtube.login.config

import com.youtube.login.services.LoginService
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
object LoginCustomRetrofit {


    private val BASE_URL = "http://10.0.2.2:9050/api/"


    @Singleton
    @Provides
    @Named("loginRetrofit")
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun providesLoginService(@Named("loginRetrofit") retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }
}