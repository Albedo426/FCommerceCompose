package com.mobilist.fcommercecompose.ui.product_detail.components.detail_images

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.mobilist.fcommercecompose.data.model.DetailProductModel
import com.mobilist.fcommercecompose.ui.components.cards.ImagePager
import com.mobilist.fcommercecompose.ui.product_detail.ProductDetailViewModel

@SuppressLint("RememberReturnType")
@ExperimentalPagerApi
@Composable
fun GetImage(state: PagerState, product: DetailProductModel, viewModel: ProductDetailViewModel = hiltViewModel()) {
    val data by remember { viewModel.listImage }
    ImagePager(state, data.map { it.productImagePath }, product)
}
