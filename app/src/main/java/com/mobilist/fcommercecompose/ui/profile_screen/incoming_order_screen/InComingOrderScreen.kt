package com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen

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
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.component.OrderItem
import com.mobilist.fcommercecompose.ui.profile_screen.user_change_screen.MainContentUserChangeScreen


@SuppressLint("RememberReturnType")
@Composable
fun InComingOrderScreen(
    navController: NavController,
    viewModel: InComingOrderViewModel = hiltViewModel(),
) {
    remember{
        viewModel.loadShoppingList()
    }
    val data by remember {viewModel.list}
    val error by remember {viewModel.errorMessage}
    val loading by remember {viewModel.isLoading}
    Log.e("TAG",data.size.toString() )
    Column(Modifier.fillMaxSize()) {
        if (error != "" || loading) {
            ErrorOnlyTextComponent(loading, error)
        } else {
            LazyColumn(Modifier.weight(0.1f).padding(bottom = 60.dp)) {
                items(data) {
                    OrderItem(navController,it)
                }
            }
        }
    }

}
