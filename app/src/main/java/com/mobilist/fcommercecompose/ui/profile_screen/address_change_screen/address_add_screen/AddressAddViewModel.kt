package com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.address_add_screen

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.user.AddressController
import com.mobilist.fcommercecompose.data.entity.user.Address
import com.mobilist.fcommercecompose.data.model.City
import com.mobilist.fcommercecompose.data.model.MyCity
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressAddViewModel @Inject constructor(
    application: Application,
    private var addressController: AddressController,
    private var customSharedPreferences: CustomSharedPreferences,
    private var userRepositoryImpl: UserRepositoryImpl
) : BaseViewModel(application)  {

    var cityMaxList = mutableStateOf<List<MyCity>>(listOf())
    var createdAddress = mutableStateOf(Address("","","",1,"",0,36000,customSharedPreferences.getUserId()!!))
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init{
        val json= City().myCityJsonString
        val gson = Gson()
        val arrayTutorialType = object : TypeToken<List<MyCity>>() {}.type
        val tutorials: List<MyCity> = gson.fromJson(json, arrayTutorialType)
        cityMaxList.value = tutorials
    }
    fun addAddress(address:Address,openDialog: MutableState<Boolean>){
        launch {
            when(val result=userRepositoryImpl.insertAddress(address)){
                is Resource.Success ->{
                    errorMessage.value=""
                    openDialog.value=true
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                }
            }
        }
    }
}