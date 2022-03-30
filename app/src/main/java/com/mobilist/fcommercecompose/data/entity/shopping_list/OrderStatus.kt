package com.mobilist.fcommercecompose.data.entity.shopping_list

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class OrderStatus(
    val status:String
){
    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}