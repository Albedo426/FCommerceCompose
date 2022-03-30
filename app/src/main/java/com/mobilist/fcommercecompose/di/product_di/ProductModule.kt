package com.mobilist.fcommercecompose.di.product_di

import com.mobilist.fcommercecompose.services.room.FCDatabase
import com.mobilist.fcommercecompose.services.room.product_api.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProductModule {
    @Provides
    @Singleton
    fun provideProductModule(appDatabase: FCDatabase): ProductDao {
        return appDatabase.productDao()
    }
}