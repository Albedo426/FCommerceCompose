package com.mobilist.fcommercecompose.ui.product_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilist.fcommercecompose.ui.product_detail.ProductDetailViewModel


@Composable
fun ProductUser(viewModel: ProductDetailViewModel = hiltViewModel()) {
    val data by remember {viewModel.product}// image var bunda ona göre ad değiştir
    Surface(
        color = Color.White, modifier = Modifier
            .background(color = Color.White)
    ) {
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            Text(
                text = "Satıcı:",
                modifier = Modifier
                    .wrapContentWidth()
                    .alpha(0.5f),
                textAlign = TextAlign.Start,
                fontSize = 14.sp
            )
            Text(
                text = data.name+" "+data.lastName,
                modifier = Modifier
                    .wrapContentWidth(),
                textAlign = TextAlign.Start,
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Satıcıya Soru Sor",
                    fontSize = 14.sp
                )
            }

        }
    }

}


