package com.mobilist.fcommercecompose.ui.categories_screen.lower_simple_category

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
class LowerSimpleCategoryViewModel @Inject constructor(
    application: Application,
    var categoryRepositoryImpl: CategoryRepositoryImpl
) : BaseViewModel(application) {
    var listCategory = mutableStateOf<List<Category>>(listOf())

    fun init(lowerCategoryId: Int) {
        getCategoriesLowerSimpleProduct(lowerCategoryId)
    }
    private fun getCategoriesLowerSimpleProduct(lowerCategoryId: Int) {
        launch {
            isLoading.value = true
            launch {
                when (val result = categoryRepositoryImpl.getCategoriesLowerSimpleProduct(lowerCategoryId)) {
                    is Resource.Success -> {
                        listCategory.value = result.data!!
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
    fun searchCategoriesLowerSimpleProduct(lowerCategoryId: Int, str: String) {
        launch {
            isLoading.value = true
            launch {
                when (val result = categoryRepositoryImpl.searchCategoriesLowerSimpleProduct(lowerCategoryId, str)) {
                    is Resource.Success -> {
                        listCategory.value = result.data!!
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
