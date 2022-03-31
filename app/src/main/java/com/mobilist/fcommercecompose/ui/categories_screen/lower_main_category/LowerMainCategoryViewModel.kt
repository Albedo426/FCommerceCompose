package com.mobilist.fcommercecompose.ui.categories_screen.lower_main_category

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
class LowerMainCategoryViewModel @Inject constructor(
    application: Application,
    var categoryRepositoryImpl: CategoryRepositoryImpl
) : BaseViewModel(application) {

    var categoryList = mutableStateOf<List<Category>>(listOf())

    fun init(mainCategoryId: Int) {
        getAllLowerMainCategories(mainCategoryId)
    }
    private fun getAllLowerMainCategories(mainCategoryId: Int,) {
        launch {
            isLoading.value = true
            launch {
                when (val result = categoryRepositoryImpl.getCategoriesLowerMainProduct(mainCategoryId)) {
                    is Resource.Success -> {
                        categoryList.value = result.data!!
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
    fun searchCategoriesLowerMainProduct(mainCategoryId: Int, str: String) {
        launch {
            isLoading.value = true
            launch {
                when (val result = categoryRepositoryImpl.searchCategoriesLowerMainProduct(mainCategoryId, str)) {
                    is Resource.Success -> {
                        categoryList.value = result.data!!
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
