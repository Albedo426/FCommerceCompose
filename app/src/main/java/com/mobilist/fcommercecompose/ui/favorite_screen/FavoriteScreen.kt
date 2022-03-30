package com.mobilist.fcommercecompose.ui.favorite_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.component.list_view.ProductCategoryListView
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.DefaultAppBar
import com.mobilist.fcommercecompose.ui.favorite_screen.component.list_view.ProductListView


@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun FavoriteScreen(
    navController: NavHostController,
    navControllerMain: NavController, viewModel: FavoriteViewModel = hiltViewModel()
) {
    val data by remember {viewModel.productList}
    val error by remember {viewModel.errorMessage}
    val loading by remember {viewModel.isLoading}
    remember{
        viewModel.init()
    }
    Column() {
        DefaultAppBar(
            title="Favorilerim",
            onSearchClicked = {
                navControllerMain.navigate("search_favorite_screen")
            },
        )
        if (error != "" || loading) {
            ErrorBasicComponent(loading = loading, error =error){
                viewModel.init()
            }
        } else {
            ProductListView(data,navControllerMain)
        }
    }
}

