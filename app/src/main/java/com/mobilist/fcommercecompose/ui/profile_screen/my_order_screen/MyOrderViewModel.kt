package com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.model.*
import com.mobilist.fcommercecompose.services.repo.order.OrderRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyOrderViewModel @Inject constructor(
    application: Application,
    private var orderRepositoryImpl: OrderRepositoryImpl,
    private var customSharedPreferences: CustomSharedPreferences
) : BaseViewModel(application) {

    var list = mutableStateOf<List<RequestOrderModel>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun loadShoppingList() {
        launch {
            when (val result = orderRepositoryImpl.getMyOrderAll(customSharedPreferences.getUserId()!!)) {
                is Resource.Success -> {
                    list.value =  result.data!!
                    errorMessage.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
            }
            isLoading.value = false
        }
    }
}
