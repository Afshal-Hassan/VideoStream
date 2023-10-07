package com.youtube.dashboard.pojos


data class VideoResponseData(
    private val id: String,
    private val title: String,
    private val url: String,
    private val uploadedAt: String,
    private val uploaderId: Long,
) {


    fun getId(): String {
        return id
    }


    fun getTitle(): String {
        return title
    }


    fun getUrl(): String {
        return url
    }


    fun getUploadedAt(): String {
        return uploadedAt
    }


    fun getUploaderId(): Long {
        return uploaderId
    }
}