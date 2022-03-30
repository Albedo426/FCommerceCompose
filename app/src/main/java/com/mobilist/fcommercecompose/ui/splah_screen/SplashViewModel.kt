package com.mobilist.fcommercecompose.ui.splah_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.MyInitData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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