package com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerBasicComponent
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component.MainContentAddressView
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component.TopBar

@Composable
fun AddressChangeScreen(
    navController: NavHostController,
    viewModel: AddressChangeViewModel = hiltViewModel()
) {
    val myAddress by remember { viewModel.myAddress }
    var error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    ErrorControllerBasicComponent(loading, error, onClick = { error = "" }) {
        Column(Modifier.fillMaxSize()) {
            TopBar("Adress Değişikliği") {
                navController.navigate("address_add_screen") // AddressAddScreen
            }
            MainContentAddressView(myAddress)
        }
    }
}
