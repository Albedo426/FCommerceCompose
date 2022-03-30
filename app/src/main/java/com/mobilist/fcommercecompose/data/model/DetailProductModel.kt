package com.mobilist.fcommercecompose.data.model


data class DetailProductModel( var productName: String="",
                               var isLike: Int? ,
                               var productMinDeclaration: String="",
                               var declaration: String="",
                               var name: String="",
                               var lastName: String="",
                               var productDiscountRate: Int=0,
                               var productPrice: Double = 0.0,
                               var finishDate: String="",
                               var UUID: Int = 0)

