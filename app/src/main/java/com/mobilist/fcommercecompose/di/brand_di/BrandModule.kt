package com.mobilist.fcommercecompose.di.brand_di

import com.mobilist.fcommercecompose.services.room.FCDatabase
import com.mobilist.fcommercecompose.services.room.brand_api.BrandDao
import com.mobilist.fcommercecompose.services.room.category_api.CategoryDao
import com.mobilist.fcommercecompose.services.room.order_api.OrderDao
import com.mobilist.fcommercecompose.services.room.product_api.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class BrandModule {
    @Provides
    @Singleton
    fun provideBrandModule(appDatabase: FCDatabase): BrandDao {
        return appDatabase.brandDao()
    }
}