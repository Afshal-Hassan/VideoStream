package com.youtube.common_utils

sealed class Activities {

    object LoginActivity : Activities()
    object SplashActivity: Activities()
    object DashboardActivity : Activities()
}
