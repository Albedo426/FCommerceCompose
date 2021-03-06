package com.mobilist.fcommercecompose.data.model

data class CommentProductModel(
    var name: String="",
    var lastName: String="",
    var userId: Int=0,


    var productId: Int=0,
    var productName: String="",
    var productMinDeclaration: String="",
    var coverImagePath: String="",

    var productPrice: Double=0.0,
    var productDiscountRate: Int=0,

    var orderStatus: Int=0,
    var orderDate: String="",
    var orderId: Int=0,

    )
