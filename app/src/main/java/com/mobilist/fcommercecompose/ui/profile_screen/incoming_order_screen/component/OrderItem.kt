package com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.ui.components.cards.MyOrderItemDefault

@Composable
fun OrderItem(navController: NavController, order: RequestOrderModel) {
    MyOrderItemDefault(order = order, onClick = {
        navController.navigate("change_order_status_screen/${order.UUID}")
    })
}
