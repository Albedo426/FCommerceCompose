package com.mobilist.fcommercecompose.data.entity.informative

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.entity.user.User

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("senderUser"),
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("UUID"),
            childColumns = arrayOf("receiverUser"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class Chat(
    @ColumnInfo(index = true)
    val senderUser: Int,
    @ColumnInfo(index = true)
    val receiverUser: Int,
    val chatText: String,
    val chatDate: String,
    val hasImage: Boolean,
){
    @PrimaryKey(autoGenerate = true)
    var UUID: Int = 0
}
