package com.youtube.dashboard.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.youtube.dashboard.databinding.ActivityDashboardBinding
import com.youtube.dashboard.viewmodels.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Dashboard : AppCompatActivity() {


    private lateinit var binding: ActivityDashboardBinding
    private val videoViewModel: VideoViewModel by viewModels()


    companion object {

        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, Dashboard::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}