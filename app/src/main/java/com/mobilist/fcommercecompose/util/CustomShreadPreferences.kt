package com.mobilist.fcommercecompose.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class CustomSharedPreferences {
    companion object {
        private const val PREFERENCES_Id = "MyId"
        private const val PREFERENCES_Init = "MyId"
        private var sharedPreferences: SharedPreferences? = null
        @Volatile private var instance: CustomSharedPreferences? = null
        private val lock = Any()
        operator fun invoke(context: Context): CustomSharedPreferences = instance ?: synchronized(
            lock
        ) {
            instance ?: makeSharedPreferences(context)
        }
        private fun makeSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }
    fun addDataToken() {
        sharedPreferences?.edit(commit = true) {
            putInt(PREFERENCES_Init, 1)
        }
    }
    fun getReadyDataToken() = sharedPreferences?.getInt(PREFERENCES_Init, 0)

    fun saveUserId(Id: Int) {
        sharedPreferences?.edit(commit = true) {
            putInt(PREFERENCES_Id, Id)
        }
    }
    fun getUserId() = sharedPreferences?.getInt(PREFERENCES_Id, 0)

    fun removeUserId() = sharedPreferences?.edit(commit = true) {
        remove(PREFERENCES_Id)
    }
}
