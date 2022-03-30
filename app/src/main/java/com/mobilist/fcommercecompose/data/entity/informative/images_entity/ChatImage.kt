package com.mobilist.fcommercecompose.data.entity.informative.images_entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.entity.informative.Chat

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Chat::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("chat"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class ChatImage(
    @ColumnInfo(index = true)
    val chat: Int,
    val path: String,
) {
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}
