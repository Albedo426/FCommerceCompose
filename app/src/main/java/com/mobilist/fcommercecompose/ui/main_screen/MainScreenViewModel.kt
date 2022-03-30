package com.mobilist.fcommercecompose.ui.main_screen

import android.app.Application
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.services.repo.user.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    application: Application,
    var userRepositoryImpl: UserRepositoryImpl
) : BaseViewModel(application) {
}
