package com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.mobilist.fcommercecompose.data.model.UserAddressModel

@Composable
fun MainContentAddressView(myAddress: List<UserAddressModel>) {
    val openDialog = remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(0.1f)) {
            items(myAddress) {
                AddressItem(it, openDialog)
            }
        }
    }
}
