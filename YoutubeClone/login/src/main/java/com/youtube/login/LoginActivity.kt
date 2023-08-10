package com.youtube.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.youtube.login.databinding.ActivityLoginBinding
import com.youtube.login.pojos.UserData
import com.youtube.login.utils.NetworkResult
import com.youtube.login.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()


    companion object {

        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        observeLogin()


        binding.loginButton.setOnClickListener {
            loginViewModel.login(
                UserData(
                    "Arhassssss",
                    "arhas@gmail.com",
                    "patistan",
                    "Pakistan",
                    "123"
                )
            )
        }

    }


    fun observeLogin() {
        loginViewModel.userLiveData.observe(this, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d("Login","Success")
                }

                is NetworkResult.Error -> {
                    Log.d("Login","Fail")
                }
                is NetworkResult.Loading -> {}
            }
        })
    }
}