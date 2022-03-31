package com.mobilist.fcommercecompose.services.room.category_api

import androidx.room.Dao
import androidx.room.Query
import com.mobilist.fcommercecompose.base.BaseDao
import com.mobilist.fcommercecompose.data.entity.product.Category
@Dao
interface CategoryDao : BaseDao<Category> {

    @Query("select * from Category where mainCategory=0  and  lowerMainCategory=0")
    suspend fun getCategoriesMainProduct(): List<Category>

    @Query("select * from Category where mainCategory=0  and  lowerMainCategory=0 and name LIKE '%' || :str || '%'")
    suspend fun searchCategoriesMainProduct(str: String): List<Category>

    @Query("select * from Category where lowerMainCategory=0 and mainCategory=:Id ")
    suspend fun getCategoriesLowerMainProduct(Id: Int): List<Category>

    @Query("select * from Category where mainCategory=:Id  and  lowerMainCategory=0 and name LIKE '%' || :str || '%'")
    suspend fun searchCategoriesLowerMainProduct(Id: Int, str: String): List<Category>

    @Query("select * from Category where mainCategory=0 and lowerMainCategory=:Id ")
    suspend fun getCategoriesLowerSimpleProduct(Id: Int): List<Category>

    @Query("select * from Category where mainCategory=0  and  lowerMainCategory=:Id  and name LIKE '%' || :str || '%'")
    suspend fun searchCategoriesLowerSimpleProduct(Id: Int, str: String): List<Category>
}
