package com.mobilist.fcommercecompose.services.room.brand_api

import androidx.room.Dao
import androidx.room.Query
import com.mobilist.fcommercecompose.base.BaseDao
import com.mobilist.fcommercecompose.data.entity.product.Brand
import com.mobilist.fcommercecompose.data.entity.product.Category


@Dao
interface BrandDao : BaseDao<Category> {
    @Query("select * from Brand")
    suspend fun getBrandList(): List<Brand>
}