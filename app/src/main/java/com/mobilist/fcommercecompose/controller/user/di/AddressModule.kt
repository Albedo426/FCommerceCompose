package com.mobilist.fcommercecompose.controller.user.di

import com.mobilist.fcommercecompose.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.controller.user.AddressController
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AddressModule {
    @Provides
    @Singleton
    fun provideUserModule(userRepositoryImpl: UserRepositoryImpl, customSharedPreferences: CustomSharedPreferences): AddressController {
        return AddressController(userRepositoryImpl,customSharedPreferences)
    }
}