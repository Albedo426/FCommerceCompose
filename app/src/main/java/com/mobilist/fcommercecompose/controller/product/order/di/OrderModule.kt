package com.mobilist.fcommercecompose.controller.product.order.di

import com.mobilist.fcommercecompose.controller.product.order.OrderController
import com.mobilist.fcommercecompose.services.repo.order.OrderRepositoryImpl
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
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
    fun provideOrderModule(orderRepositoryImpl: OrderRepositoryImpl): OrderController {
        return OrderController(orderRepositoryImpl)
    }
}
