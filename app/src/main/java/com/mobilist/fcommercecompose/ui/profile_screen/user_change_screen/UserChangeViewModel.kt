package com.mobilist.fcommercecompose.ui.profile_screen.user_change_screen

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.user.UserController
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserChangeViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    private var userRepositoryImpl: UserRepositoryImpl,
    private var userController: UserController
) : BaseViewModel(application) {

    var myUser = mutableStateOf(User("", "", "", "", "", "", "", 1))

    init {
        launch {
            userController.getUser(myUser, isLoading, errorMessage)
        }
    }
    fun changeUserNameAndMail(isSuccess: MutableState<Boolean>) {
        launch {
            when (val result = userRepositoryImpl.updateUserNamePhoneById(customSharedPreferences.getUserId()!!, myUser.value.username!!, myUser.value.phone!!)) {
                is Resource.Success -> {
                    errorMessage.value = ""
                    isSuccess.value = true
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                }
            }
        }
    }
}
