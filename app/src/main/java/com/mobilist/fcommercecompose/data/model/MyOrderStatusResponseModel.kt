package com.mobilist.fcommercecompose.data.model

data class MyOrderStatusResponseModel(
    var product: Int = 1,
    var quantity: Int = 1,//ürün adet
    var UUID: Int = 0,
    var isLike: Int?,
    var cargoName: String = "",
    var trackingNumber: String = "",
    var billAddress: String = "",
    var shipAddress: String = "",

    //user
    var name: String = "",
    var lastName: String = "",
    //Product
    var coverImagePath: String = "",
    var orderDate: String = "",
    var orderStatus: Int = 1,
    var productName: String = "",
    var productPrice: Double = 0.0,
    var productDiscountRate: Int = 1,
    var finishDate: String = ""
)

fun MyOrderStatusResponseModel.toDetailProductModel(): DetailProductModel {
    return DetailProductModel(isLike = isLike, UUID = UUID)
}
