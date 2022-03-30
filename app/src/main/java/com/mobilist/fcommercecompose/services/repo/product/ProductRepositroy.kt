package com.mobilist.fcommercecompose.services.repo.product

import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.product.ProductImage
import com.mobilist.fcommercecompose.data.entity.product.ProductMainItem
import com.mobilist.fcommercecompose.data.entity.shopping_list.Order
import com.mobilist.fcommercecompose.data.model.*

interface ProductRepositroy {
    suspend fun getCategoriesMainProduct(): Resource< List<Category>>
    suspend fun getCategoriesLowerMainProduct(Id: Int): Resource< List<Category>>
    suspend fun getCategoriesLowerSimpleProduct(Id: Int): Resource< List<Category>>
    suspend fun isLike(ProductId:Int,UserId:Int):Resource<Boolean>
    suspend fun getSearchProduct(Id:Int,str:String):  Resource<List<ProductMainItem>>
    suspend fun getSearchFavoriteProduct(Id:Int,str:String):  Resource<List<ProductMainItem>>
    suspend fun searchCategoriesMainProduct(str:String): Resource<List<Category>>
    suspend fun searchCategoriesLowerMainProduct(Id: Int,str:String): Resource<List<Category>>
    suspend fun searchCategoriesLowerSimpleProduct(Id: Int,str:String): Resource<List<Category>>
    suspend fun getSearchCategoryProduct(Id: Int,str:String):  Resource<List<ProductMainItem>>
    suspend fun getOrderStatusAll(Id: Int): Resource<MyOrderStatusResponseModel>
    suspend fun getInComingOrderAll(Id: Int): Resource< List<RequestOrderModel>>
    suspend fun getImageByProductId(Id:Int): Resource<List<ProductImage>>
    suspend fun getCommentableProduct(Id: Int,str:String): Resource< List<CommentProductModel>>
    suspend fun getHomeProduct(Id:Int): Resource<List<ProductMainItem>>
    suspend fun getFavoriteProduct(Id:Int): Resource<List<ProductMainItem>>
    suspend fun getCategoryProduct(Id:Int): Resource<List<ProductMainItem>>
    suspend fun getProductById(Id:Int): Resource<Product>
    suspend fun getProductLikeCountById(Id:Int): Resource<Int>
    suspend fun getProductScoreCountById(Id:Int): Resource<Double>
    suspend fun getProductDetailById(Id: Int,userId: Int):  Resource<DetailProductModel>
    suspend fun getProductCommentLastById(Id:Int):  Resource< List<CommentDetailModel>>
    suspend fun getMyOrder(Id:Int):  Resource< List<RequestOrderModel>>
    suspend fun getMyOrderAll(Id:Int):  Resource< List<RequestOrderModel>>
    suspend fun updateOrderStatus(  type: Int,billAddress: Int,shipAddress: Int, vararg Id:Int): Resource<Boolean>
    suspend fun updateOrderStatus(  type: Int,cargoNo: String,trackingNo: String, Id:Int): Resource<Boolean>
    suspend fun removeMyOrder(Id:Int): Resource<Boolean>
    suspend fun increaseMyOrder(Id:Int): Resource<Boolean>
    suspend fun reduceMyOrder(Id:Int): Resource<Boolean>

    suspend fun insertOrder(order: Order): Long

    suspend fun insert(product: Product):Long
    suspend fun getAllProduct(): Resource<List<Product>>
    suspend fun insertAll(vararg users: Product):List<Long>
    suspend fun allProductByCategoryId(Id:Int): List<Product>
    suspend fun allProductByName(name:String): List<Product>
}