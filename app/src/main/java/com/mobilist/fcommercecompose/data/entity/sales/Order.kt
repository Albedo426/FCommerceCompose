package com.mobilist.fcommercecompose.data.entity.sales

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.entity.user.Address
import com.mobilist.fcommercecompose.data.entity.user.Card
import com.mobilist.fcommercecompose.data.entity.user.User

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("OrderUser"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Card::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("OrderCard"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Address::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("BillAdress"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Address::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("ShipAdress"),
            onDelete = ForeignKey.CASCADE
        )]
)
//sipariş itemleri
/**
 * order bir tane order olucak bir de orderlar (orderitem) olucak içerisinde sipariş alınan ürünler olucak
 *
 * **/


data class Order(
    @ColumnInfo(index = true)
    val OrderUser: Int,
    @ColumnInfo(index = true)
    val OrderCard: Int,
    @ColumnInfo(index = true)
    val BillAdress: Int,
    @ColumnInfo(index = true)
    val ShipAdress: Int,
    val OrderDate: String,
) {

    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}
