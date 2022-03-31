package com.mobilist.fcommercecompose.services.repo.favorite

import com.mobilist.fcommercecompose.util.Resource

interface FavoriteRepository {
    suspend fun getProductLikeCountById(Id: Int): Resource<Int>
    suspend fun isLike(ProductId: Int, UserId: Int): Resource<Boolean>
}