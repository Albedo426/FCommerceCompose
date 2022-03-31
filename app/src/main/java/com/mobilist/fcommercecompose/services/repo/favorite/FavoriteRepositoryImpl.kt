package com.mobilist.fcommercecompose.services.repo.favorite

import com.mobilist.fcommercecompose.data.entity.informative.Like
import com.mobilist.fcommercecompose.services.room.favorite_api.LikeDao
import com.mobilist.fcommercecompose.util.Resource
import java.lang.Exception
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val likeDao: LikeDao) :
    FavoriteRepository {
    override suspend fun getProductLikeCountById(Id: Int): Resource<Int> {
        return try {
            Resource.Success(likeDao.getProductLikeCountById(Id))
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun isLike(ProductId: Int, UserId: Int): Resource<Boolean> {
        return try {
            val resource = likeDao.isLike(ProductId, UserId)
            if (resource.isEmpty()) {
                likeDao.insert(Like(product = ProductId, user = UserId))
                Resource.Success(true)
            } else {
                likeDao.removeLike(ProductId, UserId)
                Resource.Success(true)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
}