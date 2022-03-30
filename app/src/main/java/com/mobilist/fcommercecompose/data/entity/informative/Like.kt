package com.mobilist.fcommercecompose.data.entity.informative

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.user.User

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("user"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("product"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Like(
    @ColumnInfo(index = true)
    val user: Int,
    @ColumnInfo(index = true)
    val product: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}
