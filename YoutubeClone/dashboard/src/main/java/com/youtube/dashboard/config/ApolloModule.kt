package com.youtube.dashboard.config

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApolloModule {


    private val GRAPH_QL_BASE_URL = "http://10.0.2.2:9050/api/video"


    @Singleton
    @Provides
    fun providesApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(GRAPH_QL_BASE_URL)
            .okHttpClient(okHttpClient)
            .build()
    }
}