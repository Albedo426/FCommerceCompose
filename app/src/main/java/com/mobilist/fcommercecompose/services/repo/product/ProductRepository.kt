package com.mobilist.fcommercecompose.services.repo.product

import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.product.ProductImage
import com.mobilist.fcommercecompose.data.entity.product.ProductMainItem
import com.mobilist.fcommercecompose.data.model.*
import com.mobilist.fcommercecompose.util.Resource

interface ProductRepository {
    suspend fun getSearchProduct(Id: Int, str: String): Resource<List<ProductMainItem>>
    suspend fun getSearchFavoriteProduct(Id: Int, str: String): Resource<List<ProductMainItem>>
    suspend fun getSearchCategoryProduct(Id: Int, str: String): Resource<List<ProductMainItem>>
    suspend fun getImageByProductId(Id: Int): Resource<List<ProductImage>>
    suspend fun getHomeProduct(Id: Int): Resource<List<ProductMainItem>>
    suspend fun getFavoriteProduct(Id: Int): Resource<List<ProductMainItem>>
    suspend fun getCategoryProduct(Id: Int): Resource<List<ProductMainItem>>
    suspend fun getProductById(Id: Int): Resource<Product>
    suspend fun getProductDetailById(Id: Int, userId: Int): Resource<DetailProductModel>
    suspend fun getProductScoreCountById(Id: Int): Resource<Double>

    suspend fun insert(product: Product): Long
    suspend fun getAllProduct(): Resource<List<Product>>
    suspend fun insertAll(vararg users: Product): List<Long>
    suspend fun allProductByCategoryId(Id: Int): List<Product>
    suspend fun allProductByName(name: String): List<Product>
}
