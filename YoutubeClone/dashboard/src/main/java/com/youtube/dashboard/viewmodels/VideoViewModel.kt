package com.youtube.dashboard.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youtube.dashboard.livedata.VideoLiveData
import com.youtube.dashboard.pojos.VideoResponse
import com.youtube.dashboard.repos.VideoRepo
import com.youtube.dashboard.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VideoViewModel @Inject constructor(private val videoRepo: VideoRepo) : ViewModel() {


    val videoLiveData: LiveData<NetworkResult<VideoResponse>>
        get() = VideoLiveData.getVideoLiveData()


    fun getAllVideos() {
        viewModelScope.launch {
            videoRepo.getAllVideos("all")
        }
    }
}