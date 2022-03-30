package com.mobilist.fcommercecompose.services.room.product_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mobilist.fcommercecompose.data.entity.informative.Comment
import com.mobilist.fcommercecompose.data.entity.informative.Like
import com.mobilist.fcommercecompose.data.entity.informative.Score
import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.product.ProductImage
import com.mobilist.fcommercecompose.data.entity.product.ProductMainItem
import com.mobilist.fcommercecompose.data.entity.shopping_list.Order
import com.mobilist.fcommercecompose.data.model.*

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: Product): Long

    @Insert
    suspend fun insertOrder(order: Order): Long

    @Insert
    suspend fun insertComment(comment: Comment): Long

    @Insert
    suspend fun insertScore(score: Score): Long

    @Insert
    suspend fun insertAll(vararg users: Product): List<Long>

    @Query("SELECT * FROM Product")
    suspend fun getAllProduct(): List<Product>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID  and  myLike.user=:Id ) as isLike from Product inner join ProductPrice on Product.UUID=ProductPrice.product")
    suspend fun getHomeProduct(Id:Int): List<ProductMainItem>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID  and  myLike.user=:Id ) as isLike from Product inner join ProductPrice on Product.UUID=ProductPrice.product where Product.productName LIKE '%' || :str || '%'")
    suspend fun getSearchProduct(Id:Int,str:String): List<ProductMainItem>


    @Query("select ProductPrice.productPrice,ProductPrice.productDiscountRate,myOrder.orderDate,Product.coverImagePath,Product.productName,Product.productMinDeclaration,Product.UUID as productId,User.name,User.lastName,User.UUID as userId,myOrder.orderStatus,myOrder.UUID as orderId from `Order`as myOrder inner join ProductPrice on Product.UUID=ProductPrice.product inner join Product on Product.UUID=myOrder.product inner join User on User.UUID=myOrder.user where orderStatus=3 and myOrder.user=:Id and  Product.productName LIKE '%' || :str || '%'")
    suspend fun getCommentableProduct(Id:Int,str:String): List<CommentProductModel>

    @Query("select ProductPrice.productPrice,ProductPrice.productDiscountRate,myOrder.orderDate,Product.coverImagePath,Product.productName,Product.productMinDeclaration,Product.UUID as productId,User.name,User.lastName,User.UUID as userId,myOrder.orderStatus,myOrder.UUID as orderId from `Order`as myOrder inner join ProductPrice on Product.UUID=ProductPrice.product inner join Product on Product.UUID=myOrder.product inner join User on User.UUID=myOrder.user where orderStatus=3 and myOrder.user=:Id and  Product.UUID=:productId")
    suspend fun getCommentableProduct(Id:Int,productId:Int): CommentProductModel


    @Query("select * from 'Like' as myLike where myLike.product=:ProductId and myLike.user=:UserId")
    suspend fun isLike(ProductId: Int, UserId: Int): List<Like>

    @Insert
    suspend fun addLike(like: Like): Long

    @Query("DELETE FROM 'Like' as myLike WHERE myLike.Product=:ProductId and myLike.User=:UserId")
    suspend fun removeLike(ProductId: Int, UserId: Int)


    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike from Product inner join ProductPrice on Product.UUID=ProductPrice.product where Product.productCategory=:Id")
    suspend fun getCategoryProduct(Id: Int): List<ProductMainItem>
    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike from Product inner join ProductPrice on Product.UUID=ProductPrice.product where Product.productCategory=:Id and Product.productName LIKE '%' || :str || '%'")
    suspend fun getSearchCategoryProduct(Id: Int,str:String): List<ProductMainItem>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike from  Product inner join ProductPrice on Product.UUID=ProductPrice.product inner join 'Like' as myLike on myLike.product=Product.UUID where myLike.user=:Id")
    suspend fun getFavoriteProduct(Id: Int): List<ProductMainItem>

    @Query("select Product.productName ,Product.productMinDeclaration,Product.UUID ,quantity,ProductPrice.productPrice,ProductPrice.productDiscountRate,finishDate,startDate,coverImagePath,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike from  Product inner join ProductPrice on Product.UUID=ProductPrice.product inner join 'Like' as myLike on myLike.product=Product.UUID where myLike.user=:Id and Product.productName LIKE '%' || :str || '%'")
    suspend fun getSearchFavoriteProduct(Id: Int,str:String): List<ProductMainItem>


    @Query("SELECT * FROM ProductImage where productImageProduct=:Id")
    suspend fun getImageByProductId(Id: Int): List<ProductImage>

    @Query("SELECT myOrder.product,myOrder.quantity,myOrder.UUID ,myOrder.orderDate,myOrder.orderStatus , Product.coverImagePath,Product.productName ,ProductPrice.productPrice,ProductPrice.productDiscountRate,ProductPrice.finishDate , User.name,User.lastName FROM `Order` as myOrder inner join Product on Product.UUID=myOrder.product inner join ProductPrice on ProductPrice.product=Product.UUID inner join User on User.UUID=myOrder.user where user=:Id and orderStatus==0")
    suspend fun getMyOrder(Id: Int): List<RequestOrderModel>

    @Query("SELECT myOrder.product,myOrder.quantity,myOrder.UUID ,myOrder.orderDate,myOrder.orderStatus , Product.coverImagePath,Product.productName ,ProductPrice.productPrice,ProductPrice.productDiscountRate,ProductPrice.finishDate , User.name,User.lastName FROM `Order` as myOrder inner join Product on Product.UUID=myOrder.product inner join ProductPrice on ProductPrice.product=Product.UUID inner join User on User.UUID=myOrder.user where user=:Id ")
    suspend fun getMyOrderAll(Id: Int): List<RequestOrderModel>

    @Query("SELECT myOrder.product,myOrder.quantity,myOrder.UUID ,myOrder.orderDate,myOrder.orderStatus , Product.coverImagePath,Product.productName ,ProductPrice.productPrice,ProductPrice.productDiscountRate,ProductPrice.finishDate , User.name,User.lastName FROM `Order` as myOrder inner join Product on Product.UUID=myOrder.product inner join ProductPrice on ProductPrice.product=Product.UUID inner join User on User.UUID=myOrder.user where Product.productUser=:Id ")
    suspend fun getInComingOrderAll(Id: Int): List<RequestOrderModel>

    @Query("SELECT myOrder.product,(select UUID from `Like` as myLike where myLike.product=Product.UUID and  myLike.user=:Id ) as isLike,myOrder.UUID ,myOrder.orderDate,myOrder.cargoName,myOrder.trackingNumber,myOrder.quantity,(select address from Address where myOrder.billAddress=Address.UUID) as billAddress,(select address from Address where myOrder.shipAddress=Address.UUID) as shipAddress,myOrder.orderStatus , Product.coverImagePath,Product.productName ,ProductPrice.productPrice,ProductPrice.productDiscountRate,ProductPrice.finishDate , User.name,User.lastName FROM `Order` as myOrder inner join Product on Product.UUID=myOrder.product inner join ProductPrice on ProductPrice.product=Product.UUID inner join User on User.UUID=myOrder.user where  myOrder.UUID=:Id")
    suspend fun getOrderStatusAll(Id: Int): MyOrderStatusResponseModel

    @Query("UPDATE 'Order' SET orderStatus = :type,trackingNumber=:cargoNo,cargoName=:trackingNo WHERE UUID =:Id")
    suspend fun updateOrderStatus(type: Int, cargoNo: String, trackingNo: String, Id: Int)

    @Query("select * from Category where mainCategory=0  and  lowerMainCategory=0")
    suspend fun getCategoriesMainProduct(): List<Category>
    @Query("select * from Category where mainCategory=0  and  lowerMainCategory=0 and name LIKE '%' || :str || '%'")
    suspend fun searchCategoriesMainProduct(str:String): List<Category>

    @Query("select * from Category where lowerMainCategory=0 and mainCategory=:Id ")
    suspend fun getCategoriesLowerMainProduct(Id: Int): List<Category>
    @Query("select * from Category where mainCategory=:Id  and  lowerMainCategory=0 and name LIKE '%' || :str || '%'")
    suspend fun searchCategoriesLowerMainProduct(Id: Int,str:String): List<Category>

    @Query("select * from Category where mainCategory=0 and lowerMainCategory=:Id ")
    suspend fun getCategoriesLowerSimpleProduct(Id: Int): List<Category>
    @Query("select * from Category where mainCategory=0  and  lowerMainCategory=:Id  and name LIKE '%' || :str || '%'")
    suspend fun searchCategoriesLowerSimpleProduct(Id: Int,str:String): List<Category>

    @Query("UPDATE 'Order' SET orderStatus = :type,billAddress=:billAddress,shipAddress=:shipAddress WHERE UUID IN (:Id)")
    suspend fun updateOrderStatus(type: Int, billAddress: Int, shipAddress: Int, Id: IntArray)

    @Query("DELETE FROM 'Order' WHERE UUID = :Id")
    suspend fun removeMyOrder(Id: Int);

    @Query("UPDATE 'Order'  SET quantity=quantity+1 WHERE UUID= :Id")
    suspend fun increaseMyOrder(Id: Int);

    @Query("UPDATE 'Order'  SET quantity=quantity-1 WHERE UUID=:Id")
    suspend fun reduceMyOrder(Id: Int);


    @Query("SELECT * FROM Product where UUID=:Id")
    suspend fun getProductById(Id: Int): Product

    @Query("SELECT Product.productName,(select UUID from `Like` as myLike where myLike.product=Product.UUID and myLike.product=:Id and myLike.user=:userId) as isLike,Product.productMinDeclaration,Product.UUID,Product.declaration,myUser.name, myUser.lastName ,ProductPrice.productDiscountRate,ProductPrice.productPrice,ProductPrice.finishDate FROM Product  inner join ProductPrice on Product.UUID=ProductPrice.product inner join User as myUser on myUser.UUID=Product.UUID where Product.UUID=:Id")
    suspend fun getProductDetailById(Id: Int,userId:Int): DetailProductModel

    @Query("SELECT * FROM Comment inner join Score on Comment.user=Score.user  and Comment.product=Score.product   inner join User as user on user.UUID=Score.user where Comment.product=:Id order by Comment.UUID desc limit 10")
    suspend fun getProductCommentLastById(Id: Int): List<CommentDetailModel>


    @Query("SELECT count(*) FROM 'Like' where product=:Id")
    suspend fun getProductLikeCountById(Id: Int): Int


    @Query("SELECT AVG(scorePoint) FROM 'Score' where product=:Id")
    suspend fun getProductScoreCountById(Id: Int): Double


    @Query("SELECT * FROM Product where productCategory=:Id")
    suspend fun allProductByCategoryId(Id: Int): List<Product>

    @Query("SELECT * FROM Product where productName=:name")
    suspend fun allProductByName(name: String): List<Product>

}