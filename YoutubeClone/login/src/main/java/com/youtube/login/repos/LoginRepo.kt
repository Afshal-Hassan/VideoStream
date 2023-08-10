package com.youtube.login.repos

import LoginResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.youtube.login.livedata.UserLiveData
import com.youtube.login.pojos.UserData
import com.youtube.login.services.LoginService
import com.youtube.login.utils.NetworkResult
import com.youtube.login.utils.TokenManager
import javax.inject.Inject


class LoginRepo @Inject constructor(private val loginService: LoginService) {


    @Inject
    lateinit var tokenManager: TokenManager


    suspend fun login(userData: UserData) {
        val response = loginService.login(userData)

        if (response.isSuccessful && response.body() != null) {
            Log.d("Token", response.body()!!.getData().getToken())
            UserLiveData.getUserLiveData().postValue(NetworkResult.Success("Login Successfull", response.body()!!))
        } else if (response.errorBody() != null) {
            UserLiveData.getUserLiveData().postValue(NetworkResult.Error("Login Unsuccessfull"))
        } else {
            UserLiveData.getUserLiveData().postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}