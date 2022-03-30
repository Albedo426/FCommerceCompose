package com.mobilist.fcommercecompose.data.model

import com.mobilist.fcommercecompose.data.entity.user.User

data class LoginModel(//response request gibi adlarına ekleme yap
    var userPassword: String,
    var userEmail: String,
)

fun LoginModel.toLoginModel(): User {
    return User(userEmail = userEmail, userPassword = userPassword,
        username = null, name = null, lastName = null, tc = null, phone =null, role = null
    )
}

