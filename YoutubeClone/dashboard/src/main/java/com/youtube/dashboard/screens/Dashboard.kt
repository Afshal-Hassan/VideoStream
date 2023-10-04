package com.youtube.dashboard.screens

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.youtube.dashboard.R

class Dashboard : AppCompatActivity() {


    companion object {

        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, Dashboard::class.java)
            activity.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}