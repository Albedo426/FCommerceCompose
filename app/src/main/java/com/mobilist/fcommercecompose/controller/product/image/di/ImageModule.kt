package com.mobilist.fcommercecompose.controller.product.image.di

import com.mobilist.fcommercecompose.controller.product.image.ImageController
import com.mobilist.fcommercecompose.controller.product.order.OrderController
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ImageModule {
    @Provides
    @Singleton
    fun provideImageModule(productRepositoryImpl: ProductRepositoryImpl): ImageController {
        return ImageController(productRepositoryImpl)
    }
}
