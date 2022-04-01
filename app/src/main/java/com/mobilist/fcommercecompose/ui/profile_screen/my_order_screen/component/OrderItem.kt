package com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.ui.components.cards.MyOrderItemDefault

@Composable
fun OrderItem(order: RequestOrderModel, navController: NavController) {
    MyOrderItemDefault(order = order, onClick = {
        navController.navigate("my_order_detail_screen/${order.UUID}")
    })
}
