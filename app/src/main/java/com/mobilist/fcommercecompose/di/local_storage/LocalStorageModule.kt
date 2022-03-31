package com.mobilist.fcommercecompose.di.local_storage

import android.content.Context
import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class LocalStorageModule {
    @Provides
    @Singleton
    fun provideLocalStorageModule(@ApplicationContext appContext: Context): CustomSharedPreferences {
        return CustomSharedPreferences(appContext)
    }
}
