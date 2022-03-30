package com.mobilist.fcommercecompose.data.model

 class UserAddressModel {
     var addressTitle: String=""
     var address: String=""
     var phone: String=""
     var name: String=""
     var lastName: String=""
     var UUID:Int=0
 }
fun UserAddressModel.getFullName():String{
    return "$name $lastName"
}