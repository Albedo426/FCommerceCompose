package com.mobilist.fcommercecompose.data.entity.product

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 *
 * is main (MainCategory) go to UUId
 * */
@Entity
data class Category(
    val name: String,
    var mainCategory: Int?,//0 ise main main categori
    var lowerMainCategory: Int?,//0 ise alt main alt kategori maine bağlı // tam kategori alt maine bağlı
)
{
    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}