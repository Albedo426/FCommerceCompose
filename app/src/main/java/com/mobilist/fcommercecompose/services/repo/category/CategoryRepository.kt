package com.mobilist.fcommercecompose.services.repo.category

import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.util.Resource

interface CategoryRepository {
    suspend fun getCategoriesMainProduct(): Resource<List<Category>>
    suspend fun searchCategoriesMainProduct(str: String): Resource<List<Category>>
    suspend fun getCategoriesLowerMainProduct(Id: Int): Resource<List<Category>>
    suspend fun searchCategoriesLowerMainProduct(Id: Int, str: String): Resource<List<Category>>
    suspend fun getCategoriesLowerSimpleProduct(Id: Int): Resource<List<Category>>
    suspend fun searchCategoriesLowerSimpleProduct(Id: Int, str: String): Resource<List<Category>>
    suspend fun getCategoriesLowerProduct(): Resource<List<Category>>
}