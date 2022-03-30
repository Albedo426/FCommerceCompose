package com.mobilist.fcommercecompose.data.model

data class ProductMainItemModel (
    var productName: String,
    val isLike: Int?,
    var productPrice: Double,
    var productDiscountRate: Int,
    var coverImagePath: String,
    var productMinDeclaration: String,
    var startDate: String,
    var finishDate: String,
    var quantity: Int,
    var UUID:Int,
        )
