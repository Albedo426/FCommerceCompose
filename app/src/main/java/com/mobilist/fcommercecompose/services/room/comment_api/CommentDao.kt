package com.mobilist.fcommercecompose.services.room.comment_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mobilist.fcommercecompose.base.BaseDao
import com.mobilist.fcommercecompose.data.entity.informative.Comment
import com.mobilist.fcommercecompose.data.entity.informative.Score
import com.mobilist.fcommercecompose.data.model.CommentDetailModel
import com.mobilist.fcommercecompose.data.model.CommentProductModel

@Dao
interface CommentDao : BaseDao<Comment> {

    @Query("select ProductPrice.productPrice,ProductPrice.productDiscountRate,myOrder.orderDate,Product.coverImagePath,Product.productName,Product.productMinDeclaration,Product.UUID as productId,User.name,User.lastName,User.UUID as userId,myOrder.orderStatus,myOrder.UUID as orderId from `Order`as myOrder inner join ProductPrice on Product.UUID=ProductPrice.product inner join Product on Product.UUID=myOrder.product inner join User on User.UUID=myOrder.user where orderStatus=3 and myOrder.user=:Id and  Product.productName LIKE '%' || :str || '%'")
    suspend fun getCommentableProduct(Id: Int, str: String): List<CommentProductModel>

    @Query("select ProductPrice.productPrice,ProductPrice.productDiscountRate,myOrder.orderDate,Product.coverImagePath,Product.productName,Product.productMinDeclaration,Product.UUID as productId,User.name,User.lastName,User.UUID as userId,myOrder.orderStatus,myOrder.UUID as orderId from `Order`as myOrder inner join ProductPrice on Product.UUID=ProductPrice.product inner join Product on Product.UUID=myOrder.product inner join User on User.UUID=myOrder.user where orderStatus=3 and myOrder.user=:Id and  Product.UUID=:productId")
    suspend fun getCommentableProduct(Id: Int, productId: Int): CommentProductModel

    @Query("SELECT * FROM Comment inner join Score on Comment.user=Score.user  and Comment.product=Score.product   inner join User as user on user.UUID=Score.user where Comment.product=:Id order by Comment.UUID desc limit 10")
    suspend fun getProductCommentLastById(Id: Int): List<CommentDetailModel>



    @Insert
    suspend fun insert(score: Score)
}
