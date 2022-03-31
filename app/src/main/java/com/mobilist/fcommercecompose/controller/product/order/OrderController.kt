package com.mobilist.fcommercecompose.controller.product.order

import androidx.navigation.NavController
import com.mobilist.fcommercecompose.data.model.OrderModel
import com.mobilist.fcommercecompose.data.model.toOrder
import com.mobilist.fcommercecompose.services.repo.order.OrderRepositoryImpl
import com.mobilist.fcommercecompose.util.getNowTimeString
import javax.inject.Inject

class OrderController @Inject constructor(
    var orderRepositoryImpl: OrderRepositoryImpl
) {
    suspend fun addOrder(navController: NavController, Id: Int, productId: Int, userId: Int) {
        // if exsist olucak varsa sepette tekrar yüklenmeyecek  kontrol alınadıysa
        val order = OrderModel()
        order.orderStatus = 0
        order.orderDate = "".getNowTimeString() // time now
        order.product = productId
        order.user = userId
        order.quantity = 1
        orderRepositoryImpl.insert(order.toOrder())
        navController.navigate("shopping_list_screen/$Id")
    }
}
