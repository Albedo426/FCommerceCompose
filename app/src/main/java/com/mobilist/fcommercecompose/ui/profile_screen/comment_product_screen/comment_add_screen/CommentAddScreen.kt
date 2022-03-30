package com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen.comment_add_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.order_screen.component.OrderItem
import com.mobilist.fcommercecompose.ui.product_detail.components.rading_bar.RatingBar
import com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen.CommentProductViewModel
import com.mobilist.fcommercecompose.util.percentage


@ExperimentalComposeUiApi
@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun CommentAddScreen(
    navController: NavController,
    Id: Int,
    viewModel: CommentAddViewModel = hiltViewModel()
) {
    remember {
        viewModel.init(Id)
    }
    val data by remember { viewModel.list }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    val openDialog = remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .weight(0.1f)
                .padding(bottom = 50.dp)) {
            MainCommentAdd(navController = navController, item = data)
            AddedCommentComp(openDialog=openDialog, viewModel = viewModel)
        }
    }
    if (openDialog.value) {
        // openDialog, openDialog, editMessage
        CustomDialog(openDialog= openDialog ,text="Yorum GÖnderildi", acceptText = "Tamam", Image = R.drawable.ic_baseline_success_24 ){
            navController.navigateUp()
        }
    }

}

@Composable
fun MainCommentAdd(navController: NavController, item: CommentProductModel) {
    val circleInt = remember { 5 }
    Card(
        shape = RoundedCornerShape(circleInt), modifier = Modifier
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
            //.background(color = Color.DarkGray)
        ) {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .height(190.dp)
                    .clickable {
                        // navController.navigate("detail_product_screen/${item.UUID}")
                    },
                painter = rememberImagePainter(data = item.coverImagePath),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .padding(8.dp)
            ) {
                Column() {
                    Text(
                        item.productName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        maxLines = 1,
                        modifier = Modifier
                            .clickable {
                                //  navController.navigate("detail_product_screen/${item.UUID}")
                            }
                    )
                    Text(
                        item.productMinDeclaration,
                        maxLines = 3,
                        fontSize = 13.sp,
                    )
                    Text(
                        "Alınma Zamanı ${item.orderDate} ",
                        maxLines = 2,
                        fontSize = 13.sp,
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
                                fontSize = 15.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun AddedCommentComp(openDialog:MutableState<Boolean>, viewModel: CommentAddViewModel) {
    val circleInt = remember { 5 }
    var point by remember { viewModel.point }
    var text by remember { viewModel.text }
    Card(
        shape = RoundedCornerShape(circleInt), modifier = Modifier
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize(),

        ) {
            Column( modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Aldığınız Ürünü Aşşağıdan Puanlandırabilirsiniz",Modifier.padding(5.dp))
                RatingBar(rating = point, animateStartSize = 35, animateFinishSize = 30) {
                    point=it
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight =  300.dp).padding(8.dp),
                    maxLines=8,
                    value = text,
                    onValueChange = { text=it},
                    label = { Text("Ürün Yorumu") }
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                BasicButton(
                    text = "Yorumunu Paylaş",modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()){
                    viewModel.addComment(openDialog)
                }
            }
        }


    }
}