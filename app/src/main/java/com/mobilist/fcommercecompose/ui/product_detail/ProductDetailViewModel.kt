package com.mobilist.fcommercecompose.ui.product_detail

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.controller.product.image.ImageController
import com.mobilist.fcommercecompose.controller.product.order.OrderController
import com.mobilist.fcommercecompose.data.model.*
import com.mobilist.fcommercecompose.services.repo.comment.CommentRepositoryImpl
import com.mobilist.fcommercecompose.services.repo.favorite.FavoriteRepositoryImpl
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    application: Application,
    private var orderController: OrderController,
    private var customSharedPreferences: CustomSharedPreferences,
    private var imageController: ImageController,
    private var favoriteRepositoryImpl: FavoriteRepositoryImpl,
    private var productRepositoryImpl: ProductRepositoryImpl,
    private var commentRepositoryImpl: CommentRepositoryImpl
) : BaseViewModel(application) {

    var listImage = mutableStateOf<List<ProductImagesModel>>(listOf())
    var product = mutableStateOf(DetailProductModel(isLike = null))
    var likeCount = mutableStateOf(1)
    var comments = mutableStateOf<List<CommentDetailModel>>(listOf())
    var productScore = mutableStateOf(0.0)

    var fatalErrorMessage = mutableStateOf("")

    fun addOrder(navController: NavController, Id: Int) {
        launch {
            orderController.addOrder(navController, Id, product.value.UUID, customSharedPreferences.getUserId()!!)
        }
    }
    private fun loadImage(Id: Int) {
        launch {
            imageController.getProductImages(
                listImage,
                isLoading,
                fatalErrorMessage,
                Id
            )
        }
    }

    private fun initLoadImage(Id: Int) {
        loadImage(Id)
    }
    private fun initDataAttr(Id: Int) {
        loadDataAttr(Id)
    }
    private fun initLoadLikeCount(Id: Int) {
        loadLikeCount(Id)
    }
    private fun initLoadScore(Id: Int) {
        loadScore(Id)
    }
    private fun initLoadComment(Id: Int) {
        loadComment(Id)
    }

    fun initAll(Id: Int) {
        initLoadImage(Id)
        initDataAttr(Id)
        initLoadLikeCount(Id)
        initLoadScore(Id)
        initLoadComment(Id)
    }

    private fun loadComment(Id: Int) {
        isLoading.value = true
        launch {
            when (val result = commentRepositoryImpl.getProductCommentLastById(Id)) { // değişecek deinamik olucak
                is Resource.Success -> {
                    comments.value = result.data!!
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = "Yorum Yok"
                }
            }
            isLoading.value = false
        }
    }
    private fun loadScore(Id: Int) {
        isLoading.value = true
        launch {
            when (val result = productRepositoryImpl.getProductScoreCountById(Id)) { // değişecek deinamik olucak
                is Resource.Success -> {
                    productScore.value = result.data!!
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value = "Skor Yok"
                }
            }
            isLoading.value = false
        }
    }

    private fun loadDataAttr(Id: Int) {
        isLoading.value = true
        launch {
            when (val result = productRepositoryImpl.getProductDetailById(Id, customSharedPreferences.getUserId()!!)) { // değişecek deinamik olucak
                is Resource.Success -> {
                    product.value = result.data!!
                    fatalErrorMessage.value = ""
                }
                is Resource.Error -> {
                    fatalErrorMessage.value += "Ürün Verisi Yok \n"
                }
            }
            isLoading.value = false
        }
    }
    private fun loadLikeCount(Id: Int) {
        isLoading.value = true
        launch {
            when (val result = favoriteRepositoryImpl.getProductLikeCountById(Id)) { // değişecek deinamik olucak
                is Resource.Success -> {
                    likeCount.value = result.data!!
                    errorMessage.value = ""
                }
                is Resource.Error -> {
                    errorMessage.value += "Beğeni Yok \n"
                }
            }
            isLoading.value = false
        }
    }
}
