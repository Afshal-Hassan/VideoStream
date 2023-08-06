package com.youtube.login.navigation

import android.app.Activity
import com.youtube.common_utils.Navigator
import com.youtube.login.LoginActivity


object GoToLoginActivity : Navigator {

    override fun navigate(activity: Activity) {
        LoginActivity.launchActivity(activity)
    }
}