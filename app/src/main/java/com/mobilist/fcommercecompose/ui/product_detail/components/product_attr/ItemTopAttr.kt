package com.mobilist.fcommercecompose.ui.product_detail.components.product_attr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.product_detail.ProductDetailViewModel
import com.mobilist.fcommercecompose.ui.product_detail.components.rading_bar.RatingBar
import com.mobilist.fcommercecompose.util.percentage

@ExperimentalComposeUiApi
@Composable
fun ItemTopAttr(viewModel: ProductDetailViewModel = hiltViewModel()) {
    val data by remember {viewModel.product}
    val score by remember {viewModel.productScore}
    val likeCount by remember {viewModel.likeCount}
    Surface(
        color = Color.White, modifier = Modifier
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text =data.productName,
                modifier = Modifier
                    .wrapContentWidth(),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Text(
                text =  data.productMinDeclaration,
                modifier = Modifier
                    .wrapContentWidth()
                    .alpha(0.5f),
                textAlign = TextAlign.Start,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    text = score.toString(),
                    modifier = Modifier
                        .wrapContentWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,

                )
                RatingBar(
                        rating = score.toInt(),
                        modifier = Modifier.width(13.dp),
                        clickable = false,
                        animate = false
                    )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painterResource(R.drawable.ic_baseline_favorite_border_24),
                        modifier = Modifier.padding(4.dp),
                        contentDescription = ""
                    )
                    Text(
                        text = likeCount.toString(),
                        fontSize = 14.sp
                    )

                }


            }
            Row() {
                Text(
                    text = "Ürün Fiyatı: ",
                    modifier = Modifier
                        .wrapContentWidth()
                        .alpha(0.5f),
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp
                )
                if(data.productDiscountRate!=0){
                    Text(
                        text = "%${data.productDiscountRate} indirim ile",
                        modifier = Modifier
                            .wrapContentWidth(),
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp
                    )

                }else{
                    Text(
                        text = "${data.productPrice} ",
                        modifier = Modifier
                            .wrapContentWidth(),
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp
                    )
                }

            }
            if(data.productDiscountRate!=0){
                Text(
                    text = data.productPrice.toString(),
                    modifier = Modifier
                        .wrapContentWidth(),
                    textDecoration = TextDecoration.LineThrough,
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp
                )
                Text(
                    text = data.productPrice.percentage(data.productDiscountRate),
                    modifier = Modifier
                        .wrapContentWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp
                )
            }

        }

    }
}
