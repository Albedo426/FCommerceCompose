package com.mobilist.fcommercecompose.controller.product.image

import androidx.compose.runtime.MutableState
import com.mobilist.fcommercecompose.data.entity.product.toProductImagesModel
import com.mobilist.fcommercecompose.data.model.ProductImagesModel
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import javax.inject.Inject


class ImageController @Inject constructor(
    var productRepositoryImpl: ProductRepositoryImpl
) {
    suspend fun getProductImages(
        listImage: MutableState<List<ProductImagesModel>>,
        isLoading: MutableState<Boolean>,
        fatalErrorMessage: MutableState<String>,
        Id: Int
    ) {
        isLoading.value = true
        when (val result =
            productRepositoryImpl.getImageByProductId(Id)) {
            is Resource.Success -> {
                listImage.value = result.data!!.map { it.toProductImagesModel() }
                fatalErrorMessage.value = ""
            }
            is Resource.Error -> {
                fatalErrorMessage.value += "Resim Yok \n"
            }
        }
        isLoading.value = false
    }

}