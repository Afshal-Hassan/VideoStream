package com.youtube.dashboard.pojos

data class VideoResponse(private val message: String, private val data: List<VideoResponseData>) {


    fun getMessage(): String {
        return message
    }


    fun getData(): List<VideoResponseData> {
        return data
    }
}
