package com.youtube.dashboard.config

import com.youtube.dashboard.repos.VideoRepo
import com.youtube.dashboard.services.VideoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DashboardRepoModule {


    @Singleton
    @Provides
    fun provideVideoRepo(videoService: VideoService): VideoRepo {
        return VideoRepo(videoService)
    }
}