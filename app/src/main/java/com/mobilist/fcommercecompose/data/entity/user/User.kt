package com.mobilist.fcommercecompose.data.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mobilist.fcommercecompose.data.model.LoginModel

@Entity(
    foreignKeys = [ForeignKey(
        entity = Role::class,
        parentColumns = arrayOf("UUID"),
        childColumns = arrayOf("role"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class User(
    var username: String?,
    var userPassword: String,
    var userEmail: String,
    var name: String?,
    var lastName: String?,
    var tc: String?,
    var phone: String?,
    @ColumnInfo(index = true)
    var role: Int?
){
    @PrimaryKey(autoGenerate = true)
    var UUID:Int=0
}

fun User.toLoginModel(): LoginModel {
    return LoginModel(userEmail = userEmail, userPassword = userPassword)
}