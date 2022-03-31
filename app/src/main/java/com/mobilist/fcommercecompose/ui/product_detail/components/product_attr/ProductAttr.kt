package com.mobilist.fcommercecompose.ui.product_detail.components.product_attr

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.product_detail.ProductDetailViewModel


@Composable
fun ProductAttr(viewModel: ProductDetailViewModel = hiltViewModel()) {
    val data by remember { viewModel.product }
    Surface(
        color = Color.White,
        modifier = Modifier
            .background(color = Color.White)
    ) {

        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Ürün bilgileri",
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            MySpacerHorizontal()
            val str = data.declaration
            for (attrString in str.split("|").toTypedArray()) {
                Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Canvas(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .size(6.dp)
                    ) {
                        drawCircle(Color.Black)
                    }
                    Text(
                        text = attrString,
                        modifier = Modifier
                            .wrapContentWidth()
                            .alpha(0.5f),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                    )
                }
            }
        }
    }
}
