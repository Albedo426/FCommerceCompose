package com.mobilist.fcommercecompose.services.room.product_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mobilist.fcommercecompose.base.BaseDao
import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.product.ProductImage
import com.mobilist.fcommercecompose.data.entity.product.ProductMainItem
import com.mobilist.fcommercecompose.data.entity.product.ProductPrice
import com.mobilist.fcommercecompose.data.model.*

@Dao
interface ProductDao : BaseDao<Product> {

    @Insert
    suspend fun insertAllProduct(vararg product: Product): List<Long>
    // score
    @Query("SELECT AVG(scorePoint) FROM 'Score' where product=:Id")
    suspend fun getProductScoreCountById(Id: Int): Double

    @Query("SELECT * FROM Product ORDER BY Product.UUID DESC LIMIT 1")
    suspend fun getProductLast(): Product

    @Insert
    suspend fun insert(productPrice: ProductPrice)
    @Insert
    suspend fun insert(productImage: ProductImage)

    @Query("SELECT * FROM Product")
    suspend fun getAllProduct(): List<Product>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID  and  myLike.user=:Id ) as isLike from Product inner join ProductPrice on Product.UUID=ProductPrice.product")
    suspend fun getHomeProduct(Id: Int): List<ProductMainItem>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID  and  myLike.user=:Id ) as isLike from Product inner join ProductPrice on Product.UUID=ProductPrice.product where Product.productName LIKE '%' || :str || '%'")
    suspend fun getSearchProduct(Id: Int, str: String): List<ProductMainItem>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike from Product inner join ProductPrice on Product.UUID=ProductPrice.product where Product.productCategory=:Id")
    suspend fun getCategoryProduct(Id: Int): List<ProductMainItem>
    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike from Product inner join ProductPrice on Product.UUID=ProductPrice.product where Product.productCategory=:Id and Product.productName LIKE '%' || :str || '%'")
    suspend fun getSearchCategoryProduct(Id: Int, str: String): List<ProductMainItem>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike from  Product inner join ProductPrice on Product.UUID=ProductPrice.product inner join 'Like' as myLike on myLike.product=Product.UUID where myLike.user=:Id")
    suspend fun getFavoriteProduct(Id: Int): List<ProductMainItem>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike from  Product inner join ProductPrice on Product.UUID=ProductPrice.product inner join 'Like' as myLike on myLike.product=Product.UUID where myLike.user=:Id and Product.productName LIKE '%' || :str || '%'")
    suspend fun getSearchFavoriteProduct(Id: Int, str: String): List<ProductMainItem>

    @Query("SELECT * FROM ProductImage where productImageProduct=:Id")
    suspend fun getImageByProductId(Id: Int): List<ProductImage>

    @Query("SELECT * FROM Product where UUID=:Id")
    suspend fun getProductById(Id: Int): Product

    @Query("SELECT Product.productName,(select UUID from `Like` as myLike where myLike.product=Product.UUID and myLike.product=:Id and myLike.user=:userId) as isLike,Product.productMinDeclaration,Product.UUID,Product.declaration,myUser.name, myUser.lastName ,ProductPrice.productDiscountRate,ProductPrice.productPrice,ProductPrice.finishDate FROM Product  inner join ProductPrice on Product.UUID=ProductPrice.product inner join User as myUser on myUser.UUID=Product.productUser where Product.UUID=:Id")
    suspend fun getProductDetailById(Id: Int, userId: Int): DetailProductModel

    @Query("SELECT * FROM Product where productCategory=:Id")
    suspend fun allProductByCategoryId(Id: Int): List<Product>

    @Query("SELECT * FROM Product where productName=:name")
    suspend fun allProductByName(name: String): List<Product>
}
