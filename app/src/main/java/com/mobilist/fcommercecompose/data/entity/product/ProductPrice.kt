package com.mobilist.fcommercecompose.data.entity.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("product"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductPrice(
    @ColumnInfo(index = true)
    var product: Int,
    var productDiscountRate: Int,
    var finishDate: String,
    var startDate: String,
    var productPrice: Double,
) {
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}
