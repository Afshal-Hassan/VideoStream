package com.youtube.dashboard.repos

import android.util.Log
import com.youtube.dashboard.livedata.VideoLiveData
import com.youtube.dashboard.services.VideoService
import com.youtube.dashboard.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject


class VideoRepo @Inject constructor(private val videoService: VideoService) {


    suspend fun getAllVideos(searchType: String) {
        VideoLiveData.getVideoLiveData().postValue(NetworkResult.Loading())
        val response = videoService.getAllVideos(searchType)


        if (response.isSuccessful && response.body() != null) {

            val title = response.body()!!.getData().get(0).getTitle()
            Log.d("DATA",title)
            VideoLiveData.getVideoLiveData()
                .postValue(NetworkResult.Success(response.body()!!.getMessage(), response.body()!!))


        } else if (response.errorBody() != null) {

            val error = JSONObject(response.errorBody()!!.charStream().readText()).getString("message")
            VideoLiveData.getVideoLiveData().postValue(NetworkResult.Error(error))


        } else {
            VideoLiveData.getVideoLiveData().postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}