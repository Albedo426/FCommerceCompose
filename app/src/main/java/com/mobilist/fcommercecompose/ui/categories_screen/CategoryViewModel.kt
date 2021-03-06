package com.mobilist.fcommercecompose.ui.categories_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.services.repo.category.CategoryRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    application: Application,
    var categoryRepositoryImpl: CategoryRepositoryImpl

) : BaseViewModel(application) {
    var list = mutableStateOf<List<Category>>(listOf())
    init {
        getAllMainCategories()
    }
    private fun getAllMainCategories() {
        launch {
            isLoading.value = true
            launch {
                when (val result = categoryRepositoryImpl.getCategoriesMainProduct()) {
                    is Resource.Success -> {
                        list.value = result.data!!
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
    fun searchCategoryMain(str: String) {
        launch {
            isLoading.value = true
            launch {
                when (val result = categoryRepositoryImpl.searchCategoriesMainProduct(str)) {
                    is Resource.Success -> {
                        list.value = result.data!!
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
}
