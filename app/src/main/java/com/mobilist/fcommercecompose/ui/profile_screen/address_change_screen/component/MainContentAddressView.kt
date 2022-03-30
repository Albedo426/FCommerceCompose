package com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.model.UserAddressModel
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.main_screen.bottom_navigate_bar.NavigationItem
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.AddressChangeViewModel
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.MainContentPasswordChangeScreen

@Composable
fun MainContentAddressView( myAddress:List<UserAddressModel>) {
    val openDialog = remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(0.1f)) {
            items(myAddress) {
                AddressItem(it,openDialog)
            }
        }
    }
}

