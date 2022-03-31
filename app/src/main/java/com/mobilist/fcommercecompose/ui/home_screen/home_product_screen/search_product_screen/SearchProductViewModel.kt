package com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.search_product_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.entity.product.toProductMainItem
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    var productRepositoryImpl: ProductRepositoryImpl
) : BaseViewModel(application) {

    var productList = mutableStateOf<List<ProductMainItemModel>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    fun loadList() {
        searchData("")
    }

    fun searchData(str: String) {
        isLoading.value = true
        launch {
            when (val result = productRepositoryImpl.getSearchProduct(
                customSharedPreferences.getUserId()!!,
                str
            )) {
                is Resource.Success -> {
                    productList.value = result.data!!.map { it.toProductMainItem() }
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

