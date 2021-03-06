package com.mobilist.fcommercecompose.di.comment_api

import com.mobilist.fcommercecompose.services.room.FCDatabase
import com.mobilist.fcommercecompose.services.room.comment_api.CommentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CommentModule {
    @Provides
    @Singleton
    fun provideCommentModule(appDatabase: FCDatabase): CommentDao {
        return appDatabase.commentDao()
    }
}
