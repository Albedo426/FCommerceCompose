package com.mobilist.fcommercecompose.util

import com.mobilist.fcommercecompose.data.entity.product.*
import com.mobilist.fcommercecompose.data.entity.user.Role
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.services.repo.user.UserRepository
import com.mobilist.fcommercecompose.services.room.user_api.UserDao
import javax.inject.Inject

class MyInitData @Inject constructor(private val userDao: UserDao) {


}