package com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.model.CommentProductModel
import com.mobilist.fcommercecompose.services.repo.comment.CommentRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentProductViewModel @Inject constructor(
    application: Application,
    private var commentRepositoryImpl: CommentRepositoryImpl,
    private var customSharedPreferences: CustomSharedPreferences
) : BaseViewModel(application) {

    var list = mutableStateOf<List<CommentProductModel>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init{
        searchCategoryMain("")
    }
    fun searchCategoryMain(str:String){
        isLoading.value=true
        launch {
            when(val result=commentRepositoryImpl.getCommentableProduct(customSharedPreferences.getUserId()!!,str)){
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
}