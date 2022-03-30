package com.mobilist.fcommercecompose.data.entity.informative.images_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.entity.informative.Comment

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Comment::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("comment"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class CommentImage(
    @ColumnInfo(index = true)
    val comment: Int,
    val path: String,
) {
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}

