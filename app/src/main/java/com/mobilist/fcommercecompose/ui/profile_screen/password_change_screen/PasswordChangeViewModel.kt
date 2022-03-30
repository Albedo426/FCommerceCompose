package com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.user.UserController
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordChangeViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    private var userRepositoryImpl: UserRepositoryImpl,
    private var userController: UserController
) : BaseViewModel(application) {

    var myUser = mutableStateOf(User("", "1","","1","","","",0))
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init{
        launch {
            isLoading.value=true
            userController.getUser(myUser,isLoading,errorMessage)
            isLoading.value=false
        }
    }
    fun changeUserPassword(openDialog: MutableState<Boolean>,password:String, newPass:String){
        if(password == myUser.value.userPassword){
            launch {
                when (val result = userRepositoryImpl.updateUserPasswordById(customSharedPreferences.getUserId()!!,newPass)) {
                    is Resource.Success ->{
                        errorMessage.value=""
                        openDialog.value=true
                    }
                    is Resource.Error->{
                        errorMessage.value=result.message!!
                    }
                }
            }
        }else{
            errorMessage.value="Mevcut Şifre Hatalı"
        }

    }
}