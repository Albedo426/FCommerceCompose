package com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.my_order_detail.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.mobilist.fcommercecompose.data.entity.sales.StateOrder
import com.mobilist.fcommercecompose.data.model.toDetailProductModel
import com.mobilist.fcommercecompose.ui.components.cards.ImagePager
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.text.Title
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.my_order_detail.MyOrderDetailViewModel

@ExperimentalPagerApi
@Composable
fun MyOrderDetailItem(
    navController: NavController,
    viewModel: MyOrderDetailViewModel
) {
    val order by remember { viewModel.list }
    val images by remember { viewModel.listImage }
    val state: PagerState = rememberPagerState()
    Card(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(5),
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            ImagePager(state = state, data = images.map { it.productImagePath }, order.toDetailProductModel())
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Title(mainText = order.productName)
            }

            MySpacerHorizontal()
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Sipariş tarihi: ${order.orderDate}",
                    )
                    Text(
                        text = "${order.name} ${order.lastName}",
                    )
                }
                Text(
                    text = "Sipariş No: ${(order.UUID + 10000)}",
                )
                Text(
                    text = "Sipariş Adresi : ${order.shipAddress }",
                )
                Text(
                    text = "Fatura Adresi : ${order.billAddress }",
                )
                if (order.trackingNumber != "" && order.cargoName != "") {
                    Text(
                        text = "Kargo Adı : ${order.cargoName }",
                    )
                    Text(
                        text = "Takip Numarası : ${order.trackingNumber }",
                    )
                }
                Text(
                    text = "Kalan Ürün Sayısı: ${order.quantity}",
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                StateOrder.values()
                    .firstOrNull { it.ordinal == order.orderStatus }?.name?.let { item ->
                        Text(
                            text = "Ürün Durumu: $item",
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }
            }
        }
    }
}
