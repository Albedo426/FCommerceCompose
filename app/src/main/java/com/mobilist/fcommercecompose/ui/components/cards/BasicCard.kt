package com.mobilist.fcommercecompose.ui.components.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.mobilist.fcommercecompose.data.model.DetailProductModel
import com.mobilist.fcommercecompose.data.model.MyOrderStatusResponseModel
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.ui.components.button.MyLikeButton
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.text.StateOrderEnum
import com.mobilist.fcommercecompose.ui.components.text.TextMultiMore
import com.mobilist.fcommercecompose.ui.components.text.Title
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.component.list_view.ItemProductHome
import com.mobilist.fcommercecompose.ui.product_detail.components.detail_images.MyHorizontalPager
import com.mobilist.fcommercecompose.ui.product_detail.components.rading_bar.DotsIndicator

@Composable
fun MyOrderItemDefault(order: RequestOrderModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(5),
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Title(mainText = order.productName, "Detaylar") {
                    onClick()
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
                        StateOrderEnum(order.orderStatus) {
                            Text(text = "Ürün Durumu: $it", modifier = Modifier.padding(5.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BasicCard(
    enabled: Boolean = true,
    padding: Int = 5,
    elevation: Int = 4,
    content: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.dp)
    ) {
        Card(
            elevation = elevation.dp,
        ) {
            val contentAlpha = if (enabled) LocalContentAlpha.current else ContentAlpha.disabled
            CompositionLocalProvider(LocalContentAlpha provides contentAlpha, content = content)
        }
    }
}

@Composable
fun ClickableCategoryCard(
    string: String,
    click: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { click() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                string,
                modifier = Modifier
                    .padding(10.dp, 20.dp, 20.dp, 20.dp)
            )
        }
    }
}

@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun ProductListView(item: List<ProductMainItemModel>, navController: NavController, isSearch: Boolean = true) {
    val modifier = if (isSearch) {
        Modifier.padding(bottom = 50.dp)
    } else {
        Modifier
    }
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(item) {
            ItemProductHome(navController, it)
        }
    }
}
@SuppressLint("RememberReturnType", "UnrememberedMutableState")
@ExperimentalPagerApi
@Composable
fun ImagePager(state: PagerState, data: List<String>, product: DetailProductModel) {
    Box() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(color = Color.White)
        ) {
            MyHorizontalPager(int = data.size, state = state, data = data)
            Surface(
                color = Color.LightGray,
                modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .alpha(0.5f) // 50% opacity
                    .background(color = Color.LightGray)
                    .padding(5.dp) // 50% opacity
            ) {
                DotsIndicator(
                    totalDots = data.size,
                    selectedIndex = state.currentPage,
                    selectedColor = Color.LightGray,
                    unSelectedColor = Color.DarkGray
                )
            }
        }
        val isLike = remember { mutableStateOf(product.isLike != null) }
        MyLikeButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
            isLike, product.UUID
        )
    }
}

@Composable
fun OrderItemDetailAttr(order: MyOrderStatusResponseModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextMultiMore(
            "Sipariş tarihi: ${order.orderDate}",
            "${order.name} ${order.lastName}"
        )
    }
    TextMultiMore(
        "Sipariş No: ${(order.UUID + 10000)}",
        "Sipariş Adresi : ${order.shipAddress }",
        "Fatura Adresi : ${order.billAddress }"
    )

    if (order.trackingNumber != "" && order.cargoName != "") {
        TextMultiMore(
            "Kargo Adı : ${order.cargoName }",
            "Kargo Adı : ${order.cargoName }"
        )
    }
    Text(
        text = "Kalan Ürün Sayısı: ${order.quantity}",
        modifier = Modifier.padding(bottom = 5.dp)
    )
}
