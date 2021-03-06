package com.mobilist.fcommercecompose.ui.categories_screen.category_product.search_category_product_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.entity.product.toProductMainItem
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchCategoryProductViewModel @Inject constructor(
    application: Application,
    var productRepositoryImpl: ProductRepositoryImpl,
    var customSharedPreferences: CustomSharedPreferences
) : BaseViewModel(application) {

    var productList = mutableStateOf<List<ProductMainItemModel>>(listOf())

    fun init(Id: Int) {
        getData(Id)
    }

    private fun getData(Id: Int) {
        isLoading.value = true
        launch {
            when (val result = productRepositoryImpl.getCategoryProduct(Id)) {
                is Resource.Success -> {
                    val items = result.data!!.map { it.toProductMainItem() }
                    productList.value = items
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                }
            }
            isLoading.value = false
        }
    }
    fun searchData(Id: Int, str: String) {
        isLoading.value = true
        launch {
            when (val result = productRepositoryImpl.getSearchCategoryProduct(Id, str)) {
                is Resource.Success -> {
                    val items = result.data!!.map { it.toProductMainItem() }
                    productList.value = items
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
