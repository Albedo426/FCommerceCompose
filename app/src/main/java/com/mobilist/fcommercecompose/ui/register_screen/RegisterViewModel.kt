package com.mobilist.fcommercecompose.ui.register_screen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.model.ResponseUser
import com.mobilist.fcommercecompose.data.model.toUser
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(
    application: Application,
    var userRepositoryImpl: UserRepositoryImpl
) : BaseViewModel(application) {

    var responseUser = mutableStateOf(ResponseUser())
    var succses = mutableStateOf(false)

    fun saveUser() {
        isLoading.value = true
        launch {
            when (val result = userRepositoryImpl.insert(responseUser.value.toUser())) {
                is Resource.Success -> {
                    succses.value = true
                }
                is Resource.Error -> {
                    result.message?.let { myError(it) }
                }
            }
            isLoading.value = false
        }
    }
    private fun myError(errorMessage: String) {
        println("Resource.Error$String")
        this.errorMessage.value = errorMessage
    }
}
