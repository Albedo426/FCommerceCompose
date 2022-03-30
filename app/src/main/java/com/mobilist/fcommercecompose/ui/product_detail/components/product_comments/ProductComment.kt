package com.mobilist.fcommercecompose.ui.product_detail.components.product_comments

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.product_detail.ProductDetailViewModel


@ExperimentalComposeUiApi
@Composable
fun ProductComment(viewModel: ProductDetailViewModel = hiltViewModel()) {
    val data by remember {viewModel.comments}// image var bunda ona göre ad değiştir

    Surface(
        color = Color.White, modifier = Modifier
            .background(color = Color.White).padding(10.dp)
    ) {

        Column() {
            Row(  modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement  =  Arrangement.SpaceBetween) {
                Text(
                    text = "Ürün Yorumları",
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Tüm Yorumlar >",
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp
                )
            }
            MySpacerHorizontal()
            if(data.isNotEmpty()){
                ProductDetailComments(data)
                Text(text = "Tüm Yorumları Gör",textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth().padding(10.dp).alpha(0.8f),fontSize = 16.sp)
            }else{
                Text(text = "Henüz Yorum Yapılmamış",textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth().padding(10.dp).alpha(0.8f),fontSize = 16.sp)

            }
        }
    }

}
