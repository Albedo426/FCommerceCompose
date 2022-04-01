package com.mobilist.fcommercecompose.data.entity.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.model.KeyValueModel

/**
 *
 * is main (MainCategory) go to UUId
 * */
@Entity
data class Category(
    val name: String = "1",
    var mainCategory: Int = 1, // 0 ise main main categori
    var lowerMainCategory: Int = 1, // 0 ise alt main alt kategori maine bağlı // tam kategori alt maine bağlı
) {
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}

fun Category.toKeyValueModel() = KeyValueModel(indexId = UUID, str = name)
