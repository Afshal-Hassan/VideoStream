package com.youtube.login.services

import LoginResponse
import com.youtube.login.pojos.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface LoginService {


    @POST("auth/login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body userData: UserData): Response<LoginResponse>
}
