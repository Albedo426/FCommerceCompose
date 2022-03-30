package com.mobilist.fcommercecompose.data.entity.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.entity.product.Product

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("product"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class ProductPrice(
    @ColumnInfo(index = true)
    val product: Int,
    val productDiscountRate: Int,
    val finishDate: String,
    val startDate: String,
    val productPrice: Double,
){
    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}
