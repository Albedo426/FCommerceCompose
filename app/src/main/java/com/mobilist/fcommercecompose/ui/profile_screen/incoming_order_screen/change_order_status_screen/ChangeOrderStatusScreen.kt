package com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.main_screen.bottom_navigate_bar.NavigationItem
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen.component.OrderItem
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.PasswordChangeViewModel
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.PasswordScreenAttr


@ExperimentalPagerApi
@SuppressLint("RememberReturnType")
@Composable
fun ChangeOrderStatusScreen(
    navController: NavController,
    Id: Int,
    viewModel: ChangeOrderStatusViewModel = hiltViewModel(),
) {
    remember{
        viewModel.loadShoppingList(Id)
    }
    var error by remember {viewModel.errorMessage}
    val loading by remember {viewModel.isLoading}
    Column(Modifier.fillMaxSize()) {
        if(error!="" || loading){
            ErrorOnlyTextComponent(loading, error)
        }else{
            MainContentChangeOrderStatusScreen(navController,viewModel)
        }
    }
}


@ExperimentalPagerApi
@Composable
fun MainContentChangeOrderStatusScreen(navController: NavController, viewModel: ChangeOrderStatusViewModel) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(0.1f).padding(bottom = 60.dp)) {
            item() {
                OrderItem(navController,viewModel)
            }
        }
    }

}
