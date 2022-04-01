package com.mobilist.fcommercecompose.data.entity.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.model.KeyValueModel

@Entity
data class Brand(
    val name: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}
fun Brand.toKeyValueModel() = KeyValueModel(indexId = UUID, str = name)
