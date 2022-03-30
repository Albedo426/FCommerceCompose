package com.mobilist.fcommercecompose.ui.favorite_screen.search_favorite_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.entity.product.toProductMainItem
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchFavoriteViewModel  @Inject constructor(
    application: Application,
    var productRepositoryImpl: ProductRepositoryImpl,
    var customSharedPreferences: CustomSharedPreferences
) : BaseViewModel(application) {

    var productList= mutableStateOf<List<ProductMainItemModel>>(listOf())
    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)


    fun init(){
        searchData("")
    }

    fun searchData(str:String){
        isLoading.value=true
        launch {
            when(val result=productRepositoryImpl.getSearchFavoriteProduct(customSharedPreferences.getUserId()!!,str)){
                is Resource.Success->{
                    val items=result.data!!.map { it.toProductMainItem() }
                    productList.value=items
                    errorMessage.value=""
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                }
            }
            isLoading.value=false
        }
    }
}