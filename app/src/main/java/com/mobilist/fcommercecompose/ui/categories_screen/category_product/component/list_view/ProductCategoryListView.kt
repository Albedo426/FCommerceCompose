package com.mobilist.fcommercecompose.ui.categories_screen.category_product.component.list_view

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.CategoryProductViewModel
import com.mobilist.fcommercecompose.ui.components.cards.ProductListView
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.DefaultAppBar
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.component.list_view.ItemProductHome
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component.MainContentAddressView
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component.TopBar

@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun ProductCategoryListView(navControllerm: NavHostController, navController: NavController, id: Int, viewModel: CategoryProductViewModel = hiltViewModel(), isSearch: Boolean = true) {
    val data by remember { viewModel.productList }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    remember {
        viewModel.init(id)
    }
    Column() {
        DefaultAppBar(
            navControllerm,
            title = "Kategori",
            onSearchClicked = {
                navController.navigate("search_category_screen/$id")
            },
        )
        ErrorControllerBasicComponent(loading, error,onClick = { viewModel.init(id)}) {
            ProductListView(data, navController, isSearch)
        }
    }
}

