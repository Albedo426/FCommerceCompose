package com.mobilist.fcommercecompose.services.room.favorite_api

import androidx.room.Dao
import androidx.room.Query
import com.mobilist.fcommercecompose.base.BaseDao
import com.mobilist.fcommercecompose.data.entity.informative.Like

@Dao
interface LikeDao : BaseDao<Like> {

    @Query("select * from 'Like' as myLike where myLike.product=:ProductId and myLike.user=:UserId")
    suspend fun isLike(ProductId: Int, UserId: Int): List<Like>

    @Query("SELECT count(*) FROM 'Like' where product=:Id")
    suspend fun getProductLikeCountById(Id: Int): Int

    @Query("DELETE FROM 'Like' as myLike WHERE myLike.Product=:ProductId and myLike.User=:UserId")
    suspend fun removeLike(ProductId: Int, UserId: Int)
}
