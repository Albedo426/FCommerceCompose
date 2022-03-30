package com.mobilist.fcommercecompose.services.room.user_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mobilist.fcommercecompose.data.entity.informative.Comment
import com.mobilist.fcommercecompose.data.entity.informative.Like
import com.mobilist.fcommercecompose.data.entity.informative.Score
import com.mobilist.fcommercecompose.data.entity.product.*
import com.mobilist.fcommercecompose.data.entity.user.Address
import com.mobilist.fcommercecompose.data.entity.user.AddressType
import com.mobilist.fcommercecompose.data.entity.user.Role
import com.mobilist.fcommercecompose.data.entity.user.User
import com.mobilist.fcommercecompose.data.model.UserAddressModel

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User):Long

    @Insert
    suspend fun insertAddress(address:  Address):Long

    @Query("DELETE FROM Address WHERE UUID = :Id")
    suspend fun deleteAddressById(Id: Int);

    @Insert
    suspend fun insertAll(vararg users: User):List<Long>

    @Query("SELECT  * FROM User where userEmail=:userModelMail and userPassword=:userModelPassword")
    suspend fun loginUser(userModelMail: String,userModelPassword: String): List<User>

    @Query("SELECT * FROM User ")
    suspend fun allUser(): List<User>

    @Query("SELECT addressTitle,address,Address.phone,Address.UUID,name,lastName  FROM Address inner join User on User.UUID=Address.user where user=:Id")
    suspend fun getUserAddress(Id:Int): List<UserAddressModel>

    @Query("SELECT * FROM User where UUID=:Id")
    suspend fun getUserById(Id:Int): List<User>

    @Query("UPDATE User  SET name=:name,lastName=:lastName,phone=:phone WHERE UUID= :Id")
    suspend fun updateUserNamePhoneById(Id:Int,name:String,lastName:String,phone:String);

    @Query("UPDATE User  SET userPassword=:password where UUID=:Id")
    suspend fun updateUserPasswordById(Id:Int,password:String);

    @Query("UPDATE User  SET userEmail=:mail where UUID=:Id")
    suspend fun updateUserMailById(Id:Int,mail:String);

    //init için silinecek
    @Insert
    suspend fun insertRole(user: Role):Long
    @Insert
    suspend fun insertCategory (user: Category):Long
    @Insert
    suspend fun insertBrand(user: Brand):Long
    @Insert
    suspend fun insertProduct(user: Product):Long
    @Insert
    suspend fun insertProductPrice (user: ProductPrice):Long
    @Insert
    suspend fun insertProductImage (user: ProductImage):Long
    @Insert
    suspend fun insertLike (like: Like):Long
    @Insert
    suspend fun insertComment (comment: Comment):Long
    @Insert
    suspend fun insertScore (score: Score):Long
    @Insert
    suspend fun insertAddressType (score: AddressType):Long
}