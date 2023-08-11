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
import org.json.JSONObject
import javax.inject.Inject


class LoginRepo @Inject constructor(private val loginService: LoginService, private val tokenManager: TokenManager) {


    suspend fun login(userData: UserData) {
        UserLiveData.getUserLiveData().postValue(NetworkResult.Loading())
        val response = loginService.login(userData)


        if (response.isSuccessful && response.body() != null) {

            val token = response.body()!!.getData().getToken()
            tokenManager.saveToken(token)
            UserLiveData.getUserLiveData().postValue(NetworkResult.Success(response.body()!!.getMessage(), response.body()!!))


        } else if (response.errorBody() != null) {

            val error = JSONObject(response.errorBody()!!.charStream().readText()).getString("message")
            UserLiveData.getUserLiveData().postValue(NetworkResult.Error(error))


        } else {
            UserLiveData.getUserLiveData().postValue(NetworkResult.Error("Something went wrong"))
        }
    }


}