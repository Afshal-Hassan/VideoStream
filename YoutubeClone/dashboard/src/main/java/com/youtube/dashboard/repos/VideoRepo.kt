package com.youtube.dashboard.repos

import com.youtube.dashboard.services.VideoService
import javax.inject.Inject

class VideoRepo @Inject constructor(private val videoService: VideoService) {


    suspend fun getAllVideos() {

    }

}