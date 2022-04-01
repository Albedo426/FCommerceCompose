package com.mobilist.fcommercecompose.ui.profile_screen.product_new

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.entity.product.Brand
import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.product.ProductPrice
import com.mobilist.fcommercecompose.services.repo.brand.BrandRepositoryImpl
import com.mobilist.fcommercecompose.services.repo.category.CategoryRepositoryImpl
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.util.getNowTimeString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductNewAddViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    private var categoryRepositoryImpl: CategoryRepositoryImpl,
    private var brandRepositoryImpl: BrandRepositoryImpl,
    private var productRepositoryImpl: ProductRepositoryImpl
) : BaseViewModel(application) {

    var listCategory = mutableStateOf<List<Category>>(listOf())
    var product = mutableStateOf(Product(1, 0, 0, "", "", "", "", "", 1))
    var listBrand = mutableStateOf<List<Brand>>(listOf())
    var brand = mutableStateOf(Brand())
    var category = mutableStateOf(Category())
    var productPrice = mutableStateOf(ProductPrice(1, 0, ",", ",", 0.0))

    var productName = mutableStateOf("")
    var declaration = mutableStateOf("")
    var productMinDeclaration = mutableStateOf("")
    var imagePath1 = mutableStateOf("")
    var imagePath2 = mutableStateOf("")
    var imagePath3 = mutableStateOf("")

    init {
        loadListCategory()
        loadListBrand()
    }

    private fun loadListCategory() {
        isLoading.value = false
        launch {
            when (
                val result =
                    categoryRepositoryImpl.getCategoriesLowerProduct()
            ) {
                is Resource.Success -> {
                    listCategory.value = result.data!!
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                }
            }
        }
    }
    private fun loadListBrand() {
        isLoading.value = false
        launch {
            when (
                val result =
                    brandRepositoryImpl.getBrandList()
            ) {
                is Resource.Success -> {
                    listBrand.value = result.data!!
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                }
            }
        }
    }

    fun addProduct(openDialog: MutableState<Boolean>) {

        isLoading.value = true
        product.value.productUser = customSharedPreferences.getUserId()!!
        product.value.date = "".getNowTimeString()
        product.value.coverImagePath = imagePath1.value
        product.value.declaration = declaration.value
        product.value.productMinDeclaration = productMinDeclaration.value
        product.value.productName = productName.value

        launch {
            when (
                val result =
                    productRepositoryImpl.insertProductFull(
                        product = product.value, productPrice.value,
                        listOf(imagePath2.value, imagePath3.value).toTypedArray()
                    )
            ) {
                is Resource.Success -> {
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = result.message!!
                }
            }
            isLoading.value = false
            openDialog.value = true
        }
    }
}
