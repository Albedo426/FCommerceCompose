package com.mobilist.fcommercecompose.data.entity.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Brand(
    val name: String,)
{
    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}
