package com.youtube.dashboard.livedata

import androidx.lifecycle.MutableLiveData
import com.youtube.dashboard.pojos.VideoResponse
import com.youtube.dashboard.pojos.VideoResponseData
import com.youtube.login.utils.NetworkResult

class VideoLiveData {


    companion object {


        private val videoLiveData: MutableLiveData<NetworkResult<VideoResponse>> =
            MutableLiveData<NetworkResult<VideoResponse>>()


        fun getVideoLiveData(): MutableLiveData<NetworkResult<VideoResponse>> {
            return videoLiveData
        }


    }
}