package com.mobilist.fcommercecompose.data.entity.shopping_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.user.Address
import com.mobilist.fcommercecompose.data.entity.user.User

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("product"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("user"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Address::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("billAddress"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Address::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("shipAddress"),
            onDelete = ForeignKey.CASCADE
        )]
)

data class Order(
    @ColumnInfo(index = true)
    val product: Int,
    @ColumnInfo(index = true)
    val user: Int,
    val trackingNumber: String,//TakipNo
    val cargoName: String,//TakipNo
    val quantity: Int,//ürün adet
    val orderStatus: Int,
    val orderDate: String,//şu anki zaman
    @ColumnInfo(index = true)
    val billAddress: Int,
    @ColumnInfo(index = true)
    val shipAddress: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}