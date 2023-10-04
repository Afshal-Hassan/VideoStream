package com.youtube.clone.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
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


        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openSplashScreen()
    }


    private fun openSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            provider.getActivities(Activities.DashboardActivity).navigate(this@SplashActivity)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }, 3000)
    }
}