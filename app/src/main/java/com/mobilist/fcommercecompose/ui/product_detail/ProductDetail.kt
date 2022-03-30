package com.mobilist.fcommercecompose.ui.product_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.mobilist.fcommercecompose.R
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.colorResource
import com.mobilist.fcommercecompose.data.model.DetailProductModel
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.product_detail.components.ProductUser
import com.mobilist.fcommercecompose.ui.product_detail.components.detail_images.GetImage
import com.mobilist.fcommercecompose.ui.product_detail.components.product_attr.ItemTopAttr
import com.mobilist.fcommercecompose.ui.product_detail.components.product_attr.ProductAttr
import com.mobilist.fcommercecompose.ui.product_detail.components.product_comments.ProductComment
import com.mobilist.fcommercecompose.ui.register_screen.MyContent

@SuppressLint("RememberReturnType")
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun ProductDetail(
    navController: NavController,
    Id: Int = 0,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val state: PagerState = rememberPagerState()
    remember{
        viewModel.initAll(Id)
    }
    val error by remember { viewModel.fatalErrorMessage }
    val data by remember { viewModel.product }
    val loading by remember { viewModel.isLoading }

    Scaffold(topBar = { } ,
        floatingActionButtonPosition = FabPosition.End,
        backgroundColor =  colorResource(R.color.background_color),
        floatingActionButton = {
            if (error == "" && !loading) {ExtendedFloatingActionButton(
                text = {  Text(text = "Sepete Ekle",color = Color.White) },
                icon ={ Icon(Icons.Filled.Add,"",tint = Color.White)},
                backgroundColor =colorResource(R.color.mainBlue) ,
                onClick= {
                    viewModel.addOrder(navController,Id)
                })}
        },content = {
            if (error != "" || loading) {
                ErrorOnlyTextComponent(loading, error)
            } else {
                Content(state,data)
            }
        })
}

@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun Content( state: PagerState,  data: DetailProductModel) {
    LazyColumn() {
        item { GetImage(state,data) }
        item { ItemTopAttr() }
        item { MySpacerHorizontal(Color.LightGray) }
        item { ProductUser() }
        item { ColumnSpacer() }
        item { ProductAttr() }
        item { ColumnSpacer() }
        item { ProductComment() }
        item { ColumnSpacer() }

    }
}

@Composable
fun ColumnSpacer() {
    Spacer(modifier = Modifier.padding(top = 15.dp))
}


