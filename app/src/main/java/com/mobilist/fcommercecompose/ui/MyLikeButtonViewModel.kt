package com.mobilist.fcommercecompose.ui

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.services.repo.favorite.FavoriteRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyLikeButtonViewModel @Inject constructor(
    application: Application,
    private var customSharedPreferences: CustomSharedPreferences,
    var favoriteRepositoryImpl: FavoriteRepositoryImpl
) : BaseViewModel(application) {

    var booleanClick = mutableStateOf(false)

    fun likeClick(ProductId: Int) {
        isLoading.value = true
        launch {
            when (val result = favoriteRepositoryImpl.isLike(ProductId, customSharedPreferences.getUserId()!!)) {
                is Resource.Success -> {
                    booleanClick.value = true
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    booleanClick.value = false
                    errorMessage.value = result.message!!
                }
            }
            isLoading.value = false
        }
    }
}
