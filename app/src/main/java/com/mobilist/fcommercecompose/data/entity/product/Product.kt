package com.mobilist.fcommercecompose.data.entity.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("productUser"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Category::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("productCategory"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Brand::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("productBrand"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Product(
    @ColumnInfo(index = true)
    var productBrand: Int,
    @ColumnInfo(index = true)
    var productUser: Int,
    @ColumnInfo(index = true)
    var productCategory: Int,
    var productName: String,
    var productMinDeclaration: String,
    var declaration: String,
    var coverImagePath: String,
    var date: String,
    var quantity: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}

data class ProductMainItem(
    val productName: String,
    val isLike: Int?,
    val productMinDeclaration: String,
    val coverImagePath: String,
    val startDate: String,
    val finishDate: String,
    val productDiscountRate: Int,
    val productPrice: Double,
    val quantity: Int,
    val UUID: Int,
)

fun ProductMainItem.toProductMainItem(): ProductMainItemModel {
    return ProductMainItemModel(
        productName = productName, productMinDeclaration = productMinDeclaration,
        coverImagePath = coverImagePath, startDate = startDate, finishDate = finishDate, isLike = isLike,
        productDiscountRate = productDiscountRate, quantity = quantity, UUID = UUID, productPrice = productPrice
    )
}
