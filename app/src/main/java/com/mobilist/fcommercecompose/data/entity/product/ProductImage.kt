package com.mobilist.fcommercecompose.data.entity.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.model.ProductImagesModel

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("productImageProduct"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class ProductImage(
    val productImagePath: String,
    @ColumnInfo(index = true)
    val productImageProduct: Int,
){
    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}


fun ProductImage.toProductImagesModel(): ProductImagesModel {
    return ProductImagesModel(productImagePath = productImagePath, ProductImageProduct = productImageProduct)
}