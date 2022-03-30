package com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.my_order_detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen.ChangeOrderStatusViewModel
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.MyOrderViewModel
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.component.OrderItem
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.my_order_detail.component.MyOrderDetailItem


@ExperimentalPagerApi
@SuppressLint("RememberReturnType")
@Composable
fun MyOrderDetailScreen(
    navController: NavController,
    Id: Int,
    viewModel: MyOrderDetailViewModel = hiltViewModel(),
) {
    remember{
        viewModel.loadShoppingList(Id)
    }
    val data by remember {viewModel.list}
    val error by remember {viewModel.errorMessage}
    val loading by remember {viewModel.isLoading}
    Column(Modifier.fillMaxSize()) {
        if(error!=""){
            ErrorOnlyTextComponent(loading, error)
        }else{
            MainContentMyOrderDetailScreen(navController,viewModel)
        }
    }

}



@ExperimentalPagerApi
@Composable
fun MainContentMyOrderDetailScreen(navController: NavController, viewModel: MyOrderDetailViewModel) {
    Column(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            LazyColumn(Modifier.weight(0.1f).padding(bottom = 60.dp)) {
                item() {
                    MyOrderDetailItem(navController, viewModel)
                }
            }
        }
    }

}