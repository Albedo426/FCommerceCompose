package com.mobilist.fcommercecompose.ui.product_detail.components.detail_images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun MyHorizontalPager(int: Int, state: PagerState, data: List<String>) {
    HorizontalPager(count = int, state = state) { page ->
        Image(
            rememberImagePainter(data = data[page]),
            modifier = Modifier
                .height(350.dp),
            contentScale = ContentScale.Fit,
            contentDescription = ""
        )
    }
}
