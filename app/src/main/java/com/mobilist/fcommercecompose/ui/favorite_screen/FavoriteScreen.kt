package com.mobilist.fcommercecompose.ui.favorite_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.ui.components.cards.ProductListView
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.DefaultAppBar

@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun FavoriteScreen(
    navController: NavHostController,
    navControllerMain: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val data by remember { viewModel.productList }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    remember {
        viewModel.init()
    }
    Column() {
        DefaultAppBar(
            title = "Favorilerim",
            onSearchClicked = {
                navControllerMain.navigate("search_favorite_screen")
            },
        )
        ErrorControllerBasicComponent(loading, error,onClick = { viewModel.init()}) {
            ProductListView(data, navControllerMain)
        }
    }
}
