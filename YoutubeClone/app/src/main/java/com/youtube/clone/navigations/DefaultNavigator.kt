package com.youtube.clone.navigations

import com.youtube.common_utils.Activities
import com.youtube.common_utils.Navigator
import com.youtube.login.navigation.GoToLoginActivity

class DefaultNavigator : Navigator.Provider {

    override fun getActivities(activities: Activities): Navigator {
        return when(activities) {
            Activities.LoginActivity -> {
                GoToLoginActivity
            }

            Activities.SplashActivity -> {
                GoToLoginActivity
            }
        }
    }
}