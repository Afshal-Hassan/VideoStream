package com.youtube.dashboard.services

import com.youtube.dashboard.pojos.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface VideoService {


    @GET("/video/get/{searchType}")
    suspend fun getAllVideos(@Path("searchType") searchType: String): Response<VideoResponse>
}