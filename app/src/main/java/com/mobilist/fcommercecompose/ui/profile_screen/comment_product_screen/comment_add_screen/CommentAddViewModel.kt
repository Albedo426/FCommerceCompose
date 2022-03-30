package com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen.comment_add_screen

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.model.CommentProductModel
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentAddViewModel @Inject constructor(
    application: Application,
    private var productRepositoryImpl: ProductRepositoryImpl,
    private var customSharedPreferences: CustomSharedPreferences
) : BaseViewModel(application) {

    var list = mutableStateOf(CommentProductModel())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var point = mutableStateOf(1)
    var text = mutableStateOf("")

    fun init(Id:Int){
        searchCategoryMain(Id)
    }
    private fun searchCategoryMain(Id:Int){
        isLoading.value=true
        launch {
            when(val result=productRepositoryImpl.getCommentableProduct(customSharedPreferences.getUserId()!!,Id)){
                is Resource.Success->{
                    list.value= result.data!!
                    errorMessage.value=""
                    isLoading.value=false
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                    isLoading.value=false
                }
            }
            isLoading.value=false
        }
    }


    fun addComment(openDialog: MutableState<Boolean>){
        isLoading.value=true
        launch {
            when(val result=productRepositoryImpl.addComment(customSharedPreferences.getUserId()!!,list.value.productId,text.value,point.value)){
                is Resource.Success->{
                    errorMessage.value=""
                    openDialog.value=true
                    isLoading.value=false
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                    isLoading.value=false
                }
            }
            isLoading.value=false
        }
    }
}