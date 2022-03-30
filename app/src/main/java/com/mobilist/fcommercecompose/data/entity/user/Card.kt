package com.mobilist.fcommercecompose.data.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("UUID"),
        childColumns = arrayOf("user"),
        onDelete = ForeignKey.CASCADE
    )]
)
//masterpass kart kaydetme için sistem //son 4 hanesini kaydediceksin kart nosunun
data class Card(
    val cardNo: String,
    @ColumnInfo(index = true)
    val user: Int
){
    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}
