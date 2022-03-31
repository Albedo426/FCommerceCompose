package com.mobilist.fcommercecompose.controller.user.di

import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.controller.user.UserController
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UserModule {
    @Provides
    @Singleton
    fun provideUserModule(productRepositoryImpl: UserRepositoryImpl,customSharedPreferences: CustomSharedPreferences): UserController {
        return UserController(productRepositoryImpl,customSharedPreferences)
    }
}
