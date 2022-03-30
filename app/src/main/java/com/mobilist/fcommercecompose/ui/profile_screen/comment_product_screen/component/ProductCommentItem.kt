package com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.mobilist.fcommercecompose.data.model.CommentProductModel
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.HomeProductViewModel
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.component.list_view.MyLikeButton
import com.mobilist.fcommercecompose.util.percentage


@Composable
fun ProductCommentItem(navController: NavController, item: CommentProductModel) {//navController: NavController
    val circleInt= remember{5}
    Card(shape = RoundedCornerShape(circleInt) ,modifier = Modifier
        .padding(10.dp)) {
            Row(
                modifier = Modifier
                //.background(color = Color.DarkGray)
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(190.dp)
                        .height(170.dp)
                        .clickable {
                            // navController.navigate("detail_product_screen/${item.UUID}")
                        },
                    painter = rememberImagePainter(data =item.coverImagePath),
                    contentDescription = ""
                )
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                        .height(170.dp)
                        .padding(8.dp)
                ) {
                    Column() {
                        Text(
                            item.productName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            maxLines=1,
                            modifier = Modifier
                                .clickable {
                                    //  navController.navigate("detail_product_screen/${item.UUID}")
                                }
                        )
                        Text(
                            item.productMinDeclaration,
                            maxLines=3,
                            fontSize = 13.sp,
                        )
                        Text(
                            "Alınma Zamanı ${item.orderDate} ",
                            maxLines=2,
                            fontSize = 13.sp,
                        )
                    }
                    BasicButton(text ="Değerlendir", modifier= Modifier
                        .padding(5.dp)
                        .fillMaxWidth() ){
                        navController.navigate("comment_add_screen/${item.productId}")
                    }
                }
            }
    }
}
