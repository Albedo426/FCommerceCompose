package com.mobilist.fcommercecompose.data.model

import com.mobilist.fcommercecompose.data.entity.user.User

class ResponseUser {
    var username: String=""
    var userPassword: String=""
    var userEmail: String=""
    var name: String=""
    var lastName: String=""
    var tc: String=""
    var phone: String=""
    var role: Int = 1
    var UUID: Int = 0
}

fun ResponseUser.toUser(): User {
    val v= User(
        username = username,
        userPassword = userPassword,
        userEmail = userEmail,
        name = name,
        lastName = lastName,
        tc = tc,
        phone = phone,
        role = 1,
    )
    v.UUID=UUID
    return v
}

fun ResponseUser.isNullData():Boolean{
    return username!="" && userPassword!="" && userEmail!="" && name!="" && tc!="" && phone!=""
}