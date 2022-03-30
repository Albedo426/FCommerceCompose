package com.mobilist.fcommercecompose.controller.user

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.data.model.OrderModel
import com.mobilist.fcommercecompose.data.model.toOrder
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import javax.inject.Inject


class UserController @Inject constructor(
    var userRepositoryImpl: UserRepositoryImpl,
    var customSharedPreferences:CustomSharedPreferences
) {
    suspend fun getUser(myUser : MutableState<User>,isLoading : MutableState<Boolean>,errorMessage : MutableState<String> ){
        when(val result=userRepositoryImpl.getUserById(customSharedPreferences.getUserId()!!)){
            is Resource.Success->{
                myUser.value = result.data!!
                errorMessage.value = ""
                isLoading.value = false
            }
            is Resource.Error->{
                errorMessage.value = result.message.toString()
                isLoading.value = false
            }
        }
    }
}