package com.mobilist.fcommercecompose.ui.home_screen.home_product_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.ui.components.cards.ProductListView
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.DefaultAppBar

@SuppressLint("UnrememberedMutableState", "RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun HomeProductScreen(
    navController: NavController,
    navControllerMain: NavController,
    viewModel: HomeProductViewModel = hiltViewModel()
) {
    remember {
        viewModel.loadList()
    }
    val data by remember { viewModel.productList }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }

    Column() {
        DefaultAppBar(
            title = "Ana Sayfa",
            onSearchClicked = {
                navControllerMain.navigate("search_product_screen")
            },
        )
        ErrorControllerBasicComponent(loading, error, onClick = { viewModel.loadList() }) {
            ProductListView(data, navControllerMain)
        }
    }
}
