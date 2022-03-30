package com.mobilist.fcommercecompose.ui

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.product.order.OrderController
import com.mobilist.fcommercecompose.data.entity.product.toProductMainItem
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyLikeButtonViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    var productRepositoryImpl: ProductRepositoryImpl
) : BaseViewModel(application) {

    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)
    var booleanClick= mutableStateOf(false)

     fun likeClick(ProductId:Int){
        isLoading.value=true
        launch {
            when(val result=productRepositoryImpl.isLike(ProductId,customSharedPreferences.getUserId()!!)){
                is Resource.Success->{
                    booleanClick.value=true
                    errorMessage.value=""
                }
                is Resource.Error->{
                    booleanClick.value=false
                    errorMessage.value=result.message!!
                }
            }
            isLoading.value=false
        }
    }

}