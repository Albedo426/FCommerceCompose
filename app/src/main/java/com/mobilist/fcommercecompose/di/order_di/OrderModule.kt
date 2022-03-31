package com.mobilist.fcommercecompose.di.order_di

import com.mobilist.fcommercecompose.services.room.FCDatabase
import com.mobilist.fcommercecompose.services.room.order_api.OrderDao
import com.mobilist.fcommercecompose.services.room.product_api.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class OrderModule {
    @Provides
    @Singleton
    fun provideOrderModule(appDatabase: FCDatabase): OrderDao {
        return appDatabase.orderDao()
    }
}