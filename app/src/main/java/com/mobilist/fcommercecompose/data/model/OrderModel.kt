package com.mobilist.fcommercecompose.data.model

import com.mobilist.fcommercecompose.data.entity.shopping_list.Order

class OrderModel {
    //order
    var product: Int=1
    var user: Int=1
    var trackingNumber: String=""
    var quantity: Int=1//ürün adet
    var orderStatus: Int=1//ürün adet
    var orderDate: String=""//şu anki zaman
    var cargoName: String=""//şu anki zaman
    var billAddress: Int=1
    var shipAddress: Int=1
    var UUID: Int = 0
}

fun OrderModel.toOrder(): Order {
    val v= Order(
        billAddress = billAddress,
        orderDate = orderDate,
        orderStatus = orderStatus,
        product = product,
        quantity = quantity,
        shipAddress = shipAddress,
        trackingNumber = trackingNumber,
        cargoName = cargoName,
        user = user
    )
    v.UUID=UUID
    return v
}
