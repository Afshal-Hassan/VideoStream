package com.youtube.login.pojos

data class LoginResponseData(
    private val token: String
) {


    fun getToken(): String {
        return token
    }


}
