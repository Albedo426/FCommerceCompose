package com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.user.AddressController
import com.mobilist.fcommercecompose.data.model.UserAddressModel
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AddressChangeViewModel @Inject constructor(
    application: Application,
    private var addressController: AddressController,
    private var userRepositoryImpl: UserRepositoryImpl
) : BaseViewModel(application)  {

    var myAddress = mutableStateOf<List<UserAddressModel>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init{
        init()
    }

    fun init(){
        launch {
            addressController.getAddress(myAddress,isLoading,errorMessage)
        }
    }
    fun remove(address :UserAddressModel){
        launch {
            when(val result=userRepositoryImpl.deleteAddressById(address.UUID)){
                is Resource.Success ->{
                    errorMessage.value=""
                    init()
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                }
            }
        }
    }
}