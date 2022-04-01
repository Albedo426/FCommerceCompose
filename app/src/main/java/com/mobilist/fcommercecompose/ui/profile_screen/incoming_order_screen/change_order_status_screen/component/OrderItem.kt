package com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.entity.sales.StateOrder
import com.mobilist.fcommercecompose.data.model.toDetailProductModel
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.cards.ImagePager
import com.mobilist.fcommercecompose.ui.components.cards.OrderItemDetailAttr
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.dropdown_list.DropDownOnlyString
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.text.BasicOutlinedText
import com.mobilist.fcommercecompose.ui.components.text.StateOrderEnum
import com.mobilist.fcommercecompose.ui.components.text.Title
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen.ChangeOrderStatusViewModel

@ExperimentalPagerApi
@SuppressLint("RememberReturnType")
@Composable
fun OrderItem(
    navController: NavController,
    viewModel: ChangeOrderStatusViewModel
) {
    val openDialog = remember { mutableStateOf(false) }
    val order by remember { viewModel.list }
    var cargoName by remember { viewModel.cargoName }
    var trackingNumber by remember { viewModel.trackingNumber }
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
                OrderItemDetailAttr(order)
                StateOrderEnum(index = order.orderStatus) { item ->
                    DropDownOnlyString(
                        text = "Ürün Durumu Değiştir",
                        showString = item,
                        listAddress = StateOrder.values().map { it.name }
                    ) {
                        order.orderStatus = StateOrder.valueOf(it).ordinal
                    }
                }
                BasicOutlinedText(text = "Kargo adı") {
                    cargoName = it
                }
                BasicOutlinedText(text = "Kargo Numarası") {
                    trackingNumber = it
                }
                BasicButton(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .padding(5.dp)
                        .fillMaxWidth(),
                    text = "Kaydet"
                ) {
                    viewModel.saveOrder(openDialog)
                    openDialog.value = true
                }
            }
        }
        if (openDialog.value) {
            CustomDialog(openDialog = openDialog, text = "Sipariş İşlemi Başarılı", acceptText = "Tamam", Image = R.drawable.ic_baseline_success_24) {
                navController.navigate("in_coming_order_screen") {
                    popUpTo("change_order_status_screen") {
                        inclusive = true
                    }
                }
            }
        }
    }
}
