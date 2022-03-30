package com.mobilist.fcommercecompose.ui.home_screen.home_product_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.DefaultAppBar
import com.mobilist.fcommercecompose.ui.components.top_bar.SearchTopBar
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.component.list_view.ProductListView


@SuppressLint("UnrememberedMutableState", "RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun HomeProductScreen(
    navController: NavController, navControllerMain: NavController,viewModel: HomeProductViewModel = hiltViewModel()
) {
    remember {
        viewModel.loadList()
    }
    val data by remember { viewModel.productList }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }

    Column() {
        DefaultAppBar(
            title="Ana Sayfa",
            onSearchClicked = {
                navControllerMain.navigate("search_product_screen")
            },
        )
        if (error != "" || loading) {
            ErrorBasicComponent(loading = loading, error = error) {
                viewModel.loadList()
            }
        } else {
            ProductListView(data, navControllerMain)
        }
    }
}
