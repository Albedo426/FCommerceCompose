package com.mobilist.fcommercecompose.ui.product_detail.components.detail_images

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.mobilist.fcommercecompose.data.model.DetailProductModel
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.component.list_view.MyLikeButton
import com.mobilist.fcommercecompose.ui.product_detail.ProductDetailViewModel
import com.mobilist.fcommercecompose.ui.product_detail.components.rading_bar.DotsIndicator

@SuppressLint("RememberReturnType")
@ExperimentalPagerApi
@Composable
fun GetImage(state: PagerState, product: DetailProductModel, viewModel: ProductDetailViewModel = hiltViewModel()) {
    val data by remember { viewModel.listImage }
    ImagePager(state,data.map { it.productImagePath },product)
}
@SuppressLint("RememberReturnType", "UnrememberedMutableState")
@ExperimentalPagerApi
@Composable
fun ImagePager(state: PagerState,data: List<String>,product:DetailProductModel){
    Box() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(color = Color.White)
        ) {
            MyHorizontalPager(int = data.size, state = state, data = data)
            Surface(
                color = Color.LightGray, modifier = Modifier
                    .padding(5.dp)
                    .clip(CircleShape)
                    .alpha(0.5f)//50% opacity
                    .background(color = Color.LightGray)
                    .padding(5.dp)//50% opacity
            ) {
                DotsIndicator(
                    totalDots = data.size,
                    selectedIndex = state.currentPage,
                    selectedColor = Color.LightGray,
                    unSelectedColor = Color.DarkGray
                )
            }

        }
        val isLike=remember{ mutableStateOf( product.isLike!=null) }
        MyLikeButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),isLike,product.UUID
        )
    }
}

