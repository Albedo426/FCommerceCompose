package com.mobilist.fcommercecompose.ui.login_screen

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.model.LoginModel
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    var userRepositoryImpl: UserRepositoryImpl
) : BaseViewModel(application) {
    var loginModel = LoginModel("", "")
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var succses = mutableStateOf(false)
    init{
        //ilk yüklendiğinde çalışmıyor
        launch {
         userRepositoryImpl.init()
        }
    }
    fun login() {
        isLoading.value = true
        launch {
            println(userRepositoryImpl.getAllUser())//system.out
            when (val result = userRepositoryImpl.loginUser(loginModel)) {
                is Resource.Success -> {
                    val myUser = result.data
                    if (myUser != null) {
                        customSharedPreferences.saveUserId(myUser.UUID)
                    }
                    println("girdiiii")
                    errorMessage.value = ""
                    succses.value=true
                    isLoading.value = false
                }
                is Resource.Error -> {
                    errorMessage.value = result.message.toString()
                    isLoading.value = false
                }
            }
        }
    }

    fun goRegister() {
        //  RegisterActivity.launch(getApplication())
    }
}