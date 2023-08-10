package com.youtube.login.viewmodels

import LoginResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youtube.login.livedata.UserLiveData
import com.youtube.login.pojos.UserData
import com.youtube.login.repos.LoginRepo
import com.youtube.login.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepo) : ViewModel() {

    val userLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = UserLiveData.getUserLiveData()


    fun login(userData: UserData) {
        viewModelScope.launch {
            loginRepo.login(userData)
        }
    }
}