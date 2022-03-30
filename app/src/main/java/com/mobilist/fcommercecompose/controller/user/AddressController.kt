package com.mobilist.fcommercecompose.controller.user

import androidx.compose.runtime.MutableState
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.data.model.UserAddressModel
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import javax.inject.Inject



class AddressController @Inject constructor(
    var userRepositoryImpl: UserRepositoryImpl,
    var customSharedPreferences: CustomSharedPreferences
) {
    suspend fun getAddress(listAddress : MutableState<List<UserAddressModel>>, isLoading : MutableState<Boolean>, errorMessage : MutableState<String>){
        when (val result =
            userRepositoryImpl.getAddress(customSharedPreferences.getUserId()!!)) {
            is Resource.Success -> {
                listAddress.value = result.data!!
                errorMessage.value = ""
            }
            is Resource.Error -> {
                errorMessage.value = result.message!!
            }
        }
        isLoading.value = false
    }
}