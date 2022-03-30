package com.mobilist.fcommercecompose.data.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("user"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = AddressType::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("addressType"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Address(
    var addressTitle: String,//
    var address: String,//
    var city: String,//
    @ColumnInfo(index = true)
    var addressType: Int,//this will change (like get enum class )
    var phone: String,//
    var state: Int,
    var postalCode: Int,//
    @ColumnInfo(index = true)
    var user: Int
)
{

    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}