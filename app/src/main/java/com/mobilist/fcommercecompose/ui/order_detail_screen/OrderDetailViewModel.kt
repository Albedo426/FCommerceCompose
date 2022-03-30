package com.mobilist.fcommercecompose.ui.order_detail_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.user.AddressController
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.data.model.UserAddressModel
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.util.percentage
import com.mobilist.fcommercecompose.util.percentageDouble
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    application: Application,
    private var productRepositoryImpl: ProductRepositoryImpl,
    private var customSharedPreferences: CustomSharedPreferences,
    private var addressController: AddressController,
    private var userRepositoryImpl: UserRepositoryImpl
) : BaseViewModel(application) {

    var listAddress = mutableStateOf<List<UserAddressModel>>(listOf())
    private var listOrder = mutableStateOf<List<RequestOrderModel>>(listOf())

    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    var allPrice = mutableStateOf(0.0)

    var billAddressId = mutableStateOf(0)
    var shipAddressId = mutableStateOf(0)


    init {
        loadListOrderDetail()
        loadListAddress()
    }

    fun applyOrder() {
        launch {
            when (val result =
                productRepositoryImpl.updateOrderStatus(0,billAddressId.value,shipAddressId.value,
                    listOrder.value.map { it.UUID }.toIntArray())) {
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
            when (val result =
                productRepositoryImpl.getMyOrder(customSharedPreferences.getUserId()!!)) {
                is Resource.Success -> {
                    listOrder.value = result.data!!
                    allPrice.value = 0.0
                    listOrder.value.forEach {
                        if (it.quantity == 0) {
                            it.quantity = 1
                        }
                        allPrice.value += it.productPrice.percentageDouble(it.productDiscountRate)* it.quantity
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