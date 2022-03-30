package com.mobilist.fcommercecompose.data.model

class RequestOrderModel {
    var product: Int=1
    var quantity: Int=1//ürün adet
    var UUID: Int = 0
    //user
    var name: String=""
    var lastName: String=""
    //Product
    var coverImagePath: String=""
    var orderDate: String=""
    var orderStatus: Int=1
    var productName: String=""
    var productPrice: Double=0.0
    var productDiscountRate: Int=1
    var finishDate: String=""
}

