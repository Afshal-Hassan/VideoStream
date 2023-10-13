package com.youtube.dashboard.observers

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.youtube.dashboard.databinding.ActivityDashboardBinding
import com.youtube.dashboard.viewmodels.VideoViewModel
import com.youtube.dashboard.utils.NetworkResult

class VideoObserver {


    companion object {


        fun observeVideo(
            lifecycleOwner: AppCompatActivity,
            binding: ActivityDashboardBinding,
            videoViewModel: VideoViewModel
        ) {
            videoViewModel.videoLiveData.observe(lifecycleOwner) {
                when (it) {
                    is NetworkResult.Success -> {
                        Log.d("Get all videos", "Success")
                    }

                    is NetworkResult.Error -> {
//                        binding.errorText.text = it.message
//                        binding.errorText.visibility = View.VISIBLE
                        Log.d("Get all videos", "Error")
                    }

                    is NetworkResult.Loading -> {
                        Log.d("Get all videos", "Loading")
                    }
                }
            }
        }
    }
}