package com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen.component.OrderItem

@ExperimentalPagerApi
@SuppressLint("RememberReturnType")
@Composable
fun ChangeOrderStatusScreen(
    navController: NavController,
    Id: Int,
    viewModel: ChangeOrderStatusViewModel = hiltViewModel(),
) {
    remember {
        viewModel.loadShoppingList(Id)
    }
    var error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    Column(Modifier.fillMaxSize()) {
        ErrorControllerErrorOnlyTextComponent(loading, error) {
            MainContentChangeOrderStatusScreen(navController, viewModel)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun MainContentChangeOrderStatusScreen(navController: NavController, viewModel: ChangeOrderStatusViewModel) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(0.1f).padding(bottom = 60.dp)) {
            item() {
                OrderItem(navController, viewModel)
            }
        }
    }
}
