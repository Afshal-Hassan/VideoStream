package com.youtube.clone.screens

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.youtube.clone.R
import com.youtube.clone.databinding.ActivitySplashBinding
import com.youtube.common_utils.Activities
import com.youtube.common_utils.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {


    @Inject
    lateinit var provider: Navigator.Provider


    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this@SplashActivity, R.color.white)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openSplashScreen()
    }


    private fun openSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            provider.getActivities(Activities.LoginActivity).navigate(this@SplashActivity)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }, 3000)
    }
}