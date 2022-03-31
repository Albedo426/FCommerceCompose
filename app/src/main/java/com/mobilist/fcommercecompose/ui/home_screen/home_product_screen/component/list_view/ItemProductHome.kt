package com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.component.list_view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.ui.MyLikeButtonViewModel
import com.mobilist.fcommercecompose.ui.components.button.MyLikeButton
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.HomeProductViewModel
import com.mobilist.fcommercecompose.util.percentage

// shape = RoundedCornerShape(25),
@Composable
fun ItemProductHome(navController: NavController, item: ProductMainItemModel, viewModel: HomeProductViewModel = hiltViewModel()) { // navController: NavController
    val circleInt = remember { 5 }
    Card(
        shape = RoundedCornerShape(circleInt),
        modifier = Modifier
            .padding(10.dp)
    ) {
        Box() {
            Column() {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp)
                        .clip(
                            RoundedCornerShape(
                                topEnd = circleInt.toFloat(),
                                topStart = circleInt.toFloat()
                            )
                        )
                        .clickable {
                            navController.navigate("detail_product_screen/${item.UUID}")
                        },
                    painter = rememberImagePainter(data = item.coverImagePath),
                    contentDescription = ""
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        item.productName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        maxLines = 2,
                        modifier = Modifier
                            .width(100.dp)
                            .clickable {
                                navController.navigate("detail_product_screen/${item.UUID}")
                            }
                    )
                    Column() {
                        if (item.productDiscountRate == 0) {
                            Text(
                                "%.2f".format(item.productPrice),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = colorResource(id = R.color.text_colorDark)
                            )
                        } else {
                            Text(
                                item.productPrice.percentage(item.productDiscountRate),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = colorResource(id = R.color.text_colorDark)
                            )
                            Text(
                                "%.2f".format(item.productPrice),
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                                fontSize = 10.sp,
                                color = Color.Gray, modifier = Modifier.align(Alignment.End)
                            )
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    Row() {
                        Image(
                            painterResource(R.drawable.ic_baseline_star_border_24),
                            contentDescription = "", alpha = 0.4f
                        )
                        Text("4.5", color = Color.Gray.copy(0.4f))
                    }
                    Text(
                        "Sepete Ekle",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,

                        color = colorResource(id = R.color.mainBlue),
                        modifier = Modifier.clickable {
                            viewModel.addOrder(navController, item.UUID)
                        }
                    )
                }
            }
            val isLike = remember { mutableStateOf(item.isLike != null) }
            MyLikeButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(5.dp),
                isLike, item.UUID
            )
        }
    }
}
