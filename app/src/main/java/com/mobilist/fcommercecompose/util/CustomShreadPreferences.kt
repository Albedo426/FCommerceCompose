package com.mobilist.fcommerce.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class CustomSharedPreferences {
    companion object{
        private const val PREFERENCES_Id="MyId"
        private var sharedPreferences:SharedPreferences?=null
        @Volatile private var instance: CustomSharedPreferences?=null
        private val lock=Any()
        operator fun invoke(context: Context) : CustomSharedPreferences=instance ?: synchronized(lock){
            instance?:makeSharedPreferences(context)
        }
        private fun makeSharedPreferences(context: Context):CustomSharedPreferences{
            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveUserId(Id:Int){
        sharedPreferences?.edit(commit =  true){
            putInt(PREFERENCES_Id,Id)
        }
    }
    fun getUserId()= sharedPreferences?.getInt(PREFERENCES_Id,0)
    fun removeUserId()=sharedPreferences?.edit(commit =  true){
        remove(PREFERENCES_Id)
    }
}