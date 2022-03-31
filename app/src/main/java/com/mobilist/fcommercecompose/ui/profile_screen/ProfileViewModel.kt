package com.mobilist.fcommercecompose.ui.profile_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.user.UserController
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.util.popUpToTop
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    private var userController: UserController,
    private var userRepositoryImpl: UserRepositoryImpl
) : BaseViewModel(application) {
    var myUser = mutableStateOf(User("", "", "", "", "", "", "", 0))

    init {
        getUser()
    }

    fun exitClick(navController: NavController) {
        customSharedPreferences.removeUserId()
        navController.navigate("login_screen") {
            popUpToTop(navController)
        }
    }
    private fun getUser() {
        isLoading.value = true
        launch {
            userController.getUser(myUser, isLoading, errorMessage)
        }
    }
}
