package com.mobilist.fcommercecompose.ui.order_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.model.*
import com.mobilist.fcommercecompose.services.repo.order.OrderRepositoryImpl
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.util.percentageDouble
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    private var orderRepositoryImpl: OrderRepositoryImpl
) : BaseViewModel(application) {

    var list = mutableStateOf<List<RequestOrderModel>>(listOf())
    var allPrice = mutableStateOf(0.0)

    private suspend fun quantityController(plus: Boolean, price: Double, UUID: Int): Resource<Boolean> {
        return if (plus) {
            allPrice.value += price
            orderRepositoryImpl.increaseMyOrder(UUID)
        } else {
            allPrice.value -= price
            orderRepositoryImpl.reduceMyOrder(UUID)
        }
    }
    fun changeAllPrice(plus: Boolean, price: Double, UUID: Int) {
        launch {
            when (val result = quantityController(plus, price, UUID)) { // değişecek deinamik olucak
                is Resource.Success -> {
                    errorMessage.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
            }
            isLoading.value = false
            loadShoppingList()
        }
    }
    fun remove(order: RequestOrderModel) {
        launch {
            when (val result = orderRepositoryImpl.removeMyOrder(order.UUID)) { // değişecek deinamik olucak
                is Resource.Success -> {
                    val items = result.data!!
                    allPrice.value = 0.0
                    list.value = emptyList()
                    loadShoppingList()
                    errorMessage.value = ""
                    isLoading.value = false
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }
            }
            isLoading.value = false
            loadShoppingList()
        }
    }

    fun loadShoppingList() {
        launch {
            when (val result = orderRepositoryImpl.getMyOrder(customSharedPreferences.getUserId()!!)) {
                is Resource.Success -> {
                    val items = result.data!!
                    list.value = items
                    allPrice.value = 0.0
                    items.forEach {
                        if (it.quantity == 0) {
                            it.quantity = 1
                        }
                        allPrice.value += it.productPrice.percentageDouble(it.productDiscountRate) * it.quantity
                    }
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
