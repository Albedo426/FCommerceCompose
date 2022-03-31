package com.mobilist.fcommercecompose.ui.order_detail_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.user.AddressController
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.data.model.UserAddressModel
import com.mobilist.fcommercecompose.services.repo.order.OrderRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.util.percentageDouble
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    private var addressController: AddressController,
    private var orderRepositoryImpl: OrderRepositoryImpl
) : BaseViewModel(application) {

    var listAddress = mutableStateOf<List<UserAddressModel>>(listOf())
    private var listOrder = mutableStateOf<List<RequestOrderModel>>(listOf())

    var allPrice = mutableStateOf(0.0)

    var billAddressId = mutableStateOf(0)
    var shipAddressId = mutableStateOf(0)

    init {
        loadListOrderDetail()
        loadListAddress()
    }

    fun applyOrder() {
        launch {
            when (
                val result =
                    orderRepositoryImpl.updateOrderStatus(
                        1, billAddressId.value, shipAddressId.value,
                        listOrder.value.map { it.UUID }.toIntArray()
                    )
            ) {
                is Resource.Success -> {
                    allPrice.value = 0.0
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                }
            }
        }
    }
    private fun loadListAddress() {
        isLoading.value = true
        launch {
            addressController.getAddress(listAddress, isLoading, errorMessage)
            isLoading.value = false
        }
    }

    private fun loadListOrderDetail() {
        isLoading.value = true
        launch {
            when (
                val result =
                    orderRepositoryImpl.getMyOrder(customSharedPreferences.getUserId()!!)
            ) {
                is Resource.Success -> {
                    listOrder.value = result.data!!
                    allPrice.value = 0.0
                    listOrder.value.forEach {
                        if (it.quantity == 0) {
                            it.quantity = 1
                        }
                        allPrice.value += it.productPrice.percentageDouble(it.productDiscountRate) * it.quantity
                    }
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                }
            }
            isLoading.value = false
        }
    }
}
