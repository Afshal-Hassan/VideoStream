package com.youtube.dashboard.navigation

import android.app.Activity
import com.youtube.common_utils.Navigator
import com.youtube.dashboard.screens.Dashboard

object GoToDashboardActivity : Navigator {

    override fun navigate(activity: Activity) {
        Dashboard.launchActivity(activity)
    }
}