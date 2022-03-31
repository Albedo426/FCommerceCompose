package com.mobilist.fcommercecompose.di.favorite_di

import com.mobilist.fcommercecompose.services.room.FCDatabase
import com.mobilist.fcommercecompose.services.room.category_api.CategoryDao
import com.mobilist.fcommercecompose.services.room.comment_api.CommentDao
import com.mobilist.fcommercecompose.services.room.favorite_api.LikeDao
import com.mobilist.fcommercecompose.services.room.order_api.OrderDao
import com.mobilist.fcommercecompose.services.room.product_api.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FavoriteModule {
    @Provides
    @Singleton
    fun provideFavoriteModule(appDatabase: FCDatabase): LikeDao {
        return appDatabase.likeDao()
    }
}