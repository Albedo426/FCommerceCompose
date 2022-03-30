package com.mobilist.fcommercecompose.data.entity.sales

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("Order"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Cart(
    @ColumnInfo(index = true)
    val Order: Int,
    val Quantity: Int,

){

    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}
