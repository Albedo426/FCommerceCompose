package com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.component.OrderItem

@SuppressLint("RememberReturnType")
@Composable
fun MyOrderScreen(
    navController: NavController,
    viewModel: MyOrderViewModel = hiltViewModel(),
) {
    remember {
        viewModel.loadShoppingList()
    }
    val data by remember { viewModel.list }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    Log.e("TAG", data.size.toString())
    Column(Modifier.fillMaxSize()) {
        ErrorControllerErrorOnlyTextComponent(loading, error) {
            LazyColumn(Modifier.weight(0.1f).padding(bottom = 60.dp)) {
                items(data) {
                    OrderItem(it, navController)
                }
            }
        }
    }
}
