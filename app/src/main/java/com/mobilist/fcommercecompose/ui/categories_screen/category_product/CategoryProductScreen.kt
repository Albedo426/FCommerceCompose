package com.mobilist.fcommercecompose.ui.categories_screen.category_product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.component.list_view.ProductCategoryListView

@ExperimentalFoundationApi
@Composable
fun CategoryProductScreen(
    navController: NavHostController,
    navControllerMain: NavController,
    Id: Int
) {
    Column() {
        ProductCategoryListView(navController, navControllerMain, Id)
    }
}
