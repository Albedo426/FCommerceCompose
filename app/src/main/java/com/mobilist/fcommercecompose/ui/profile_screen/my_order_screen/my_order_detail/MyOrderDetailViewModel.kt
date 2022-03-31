package com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.my_order_detail

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.product.image.ImageController
import com.mobilist.fcommercecompose.data.model.MyOrderStatusResponseModel
import com.mobilist.fcommercecompose.data.model.ProductImagesModel
import com.mobilist.fcommercecompose.services.repo.order.OrderRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyOrderDetailViewModel @Inject constructor(
    application: Application,
    private var orderRepositoryImpl: OrderRepositoryImpl,
    private var imageController: ImageController
) : BaseViewModel(application) {

    var list = mutableStateOf(MyOrderStatusResponseModel(isLike = null))
    var cargoName = mutableStateOf("")
    var trackingNumber = mutableStateOf("")
    var listImage = mutableStateOf<List<ProductImagesModel>>(listOf())


    private suspend fun loadImage(Id: Int) {
        imageController.getProductImages(
            listImage,
            isLoading,
            errorMessage,
            Id
        )
    }
    fun loadShoppingList(Id: Int) {
        isLoading.value = true
        launch {
            when (val result = orderRepositoryImpl.getOrderStatusAll(Id)) {
                is Resource.Success -> {
                    val items = result.data!!
                    loadImage(items.product)
                    list.value = items
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
