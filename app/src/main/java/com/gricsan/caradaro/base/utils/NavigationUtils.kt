package com.gricsan.caradaro.base.utils

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDirections

private const val SAFE_NAVIGATION_TAG = "SAFE_NAVIGATION_TAG"


fun NavController.safeNavigate(action: NavDirections) {
    try {
        navigate(action)
    } catch (t: Throwable) {
        Log.e(SAFE_NAVIGATION_TAG, "Navigation error for action $action")
    }
}