package com.mobilist.fcommercecompose.services.room.order_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mobilist.fcommercecompose.base.BaseDao
import com.mobilist.fcommercecompose.data.entity.shopping_list.Order
import com.mobilist.fcommercecompose.data.model.MyOrderStatusResponseModel
import com.mobilist.fcommercecompose.data.model.RequestOrderModel

@Dao
interface OrderDao : BaseDao<Order> {

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

    @Query("UPDATE 'Order' SET orderStatus = :type,billAddress=:billAddress,shipAddress=:shipAddress WHERE UUID IN (:Id)")
    suspend fun updateOrderStatus(type: Int, billAddress: Int, shipAddress: Int, Id: IntArray)

    @Query("DELETE FROM 'Order' WHERE UUID = :Id")
    suspend fun removeMyOrder(Id: Int)

    @Query("UPDATE 'Order'  SET quantity=quantity+1 WHERE UUID= :Id")
    suspend fun increaseMyOrder(Id: Int)

    @Query("UPDATE 'Order'  SET quantity=quantity-1 WHERE UUID=:Id")
    suspend fun reduceMyOrder(Id: Int)
}