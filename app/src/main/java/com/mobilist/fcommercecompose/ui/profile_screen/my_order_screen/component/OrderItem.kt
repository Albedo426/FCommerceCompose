package com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mobilist.fcommercecompose.data.entity.sales.StateOrder
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.text.Title

@Composable
fun OrderItem(order: RequestOrderModel, navController: NavController) {
    Card(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(5),
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Title(mainText = order.productName, "Detaylar"){
                    navController.navigate("my_order_detail_screen/${order.UUID}")
                }
            }
            MySpacerHorizontal()
            Column(Modifier.fillMaxHeight()) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(
                        rememberImagePainter(data = order.coverImagePath),
                        modifier = Modifier
                            .width(120.dp)
                            .height(120.dp),
                        contentDescription = "",
                    )
                    Column(Modifier.padding(10.dp)) {
                        Text(
                            text = "Sipariş tarihi: ${order.orderDate}",
                            modifier = Modifier.padding(5.dp)
                        )
                        StateOrder.values()
                            .firstOrNull { it.ordinal == order.orderStatus }?.name?.let {
                            Text(text = "Ürün Durumu: $it", modifier = Modifier.padding(5.dp))
                        }
                    }
                }

            }

        }
    }
}