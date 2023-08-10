package com.youtube.login.livedata

import LoginResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.youtube.login.utils.NetworkResult

class UserLiveData {


    companion object {


        private val userLiveData: MutableLiveData<NetworkResult<LoginResponse>> =
            MutableLiveData<NetworkResult<LoginResponse>>()


        fun getUserLiveData(): MutableLiveData<NetworkResult<LoginResponse>> {
            return userLiveData
        }


    }

}