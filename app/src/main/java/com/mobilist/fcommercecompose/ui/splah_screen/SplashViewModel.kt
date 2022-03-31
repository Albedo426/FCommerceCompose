package com.mobilist.fcommercecompose.ui.splah_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel  @Inject constructor(
    application: Application,
    var customSharedPreferences: CustomSharedPreferences,
) : BaseViewModel(application) {
    var route = mutableStateOf(false)
    fun routerControl():Boolean {//chackifUserLogIn
        //customSharedPreferences.removeUserId();
        val userId = customSharedPreferences.getUserId()
        route.value = userId != null && userId != 0
        return route.value
    }
}