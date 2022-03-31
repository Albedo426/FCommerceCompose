package com.mobilist.fcommercecompose.services.repo.order

import com.mobilist.fcommercecompose.data.entity.shopping_list.Order
import com.mobilist.fcommercecompose.data.model.MyOrderStatusResponseModel
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.util.Resource

interface OrderRepository {
    suspend fun insert(order: Order): Resource<Boolean>
    suspend fun getMyOrder(Id: Int): Resource< List<RequestOrderModel>>
    suspend fun getMyOrderAll(Id: Int): Resource< List<RequestOrderModel>>
    suspend fun getInComingOrderAll(Id: Int): Resource< List<RequestOrderModel>>
    suspend fun getOrderStatusAll(Id: Int): Resource<MyOrderStatusResponseModel>
    suspend fun updateOrderStatus(type: Int, cargoNo: String, trackingNo: String, Id: Int): Resource<Boolean>
    suspend fun updateOrderStatus(type: Int, billAddress: Int, shipAddress: Int, Id: IntArray): Resource<Boolean>
    suspend fun reduceMyOrder(Id: Int): Resource<Boolean>
    suspend fun increaseMyOrder(Id: Int): Resource<Boolean>
    suspend fun removeMyOrder(Id: Int): Resource<Boolean>
}
