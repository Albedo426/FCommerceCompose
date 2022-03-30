package com.mobilist.fcommercecompose.services.repo.user

import com.mobilist.fcommercecompose.data.entity.user.Address
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.data.model.LoginModel
import com.mobilist.fcommercecompose.data.model.UserAddressModel

interface UserRepository {
    suspend fun insert(user: User): Resource<Long>
    suspend fun insertAll(vararg users: User):List<Long>
    suspend fun loginUser(user: LoginModel): Resource<User>
    suspend fun getAddress(Id: Int): Resource<List<UserAddressModel>>
    suspend fun insertAddress(user: Address): Resource<Long>
    suspend fun deleteAddressById(Id: Int): Resource<Boolean>
    suspend fun getUserById(Id: Int): Resource<User>
    suspend fun updateUserNamePhoneById(Id:Int,name:String,phone:String): Resource<Boolean>
    suspend fun updateUserPasswordById(Id:Int,password:String): Resource<Boolean>
    suspend fun updateUserMailById(Id:Int,mail:String): Resource<Boolean>
    suspend fun getAllUser():List<User>
    suspend fun init()
}