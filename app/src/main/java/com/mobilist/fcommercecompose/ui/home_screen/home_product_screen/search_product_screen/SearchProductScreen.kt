package com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.search_product_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.ui.components.cards.ProductListView
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.SearchTopBar

@ExperimentalFoundationApi
@Composable
fun SearchProductScreen(
    navController: NavController,
    viewModel: SearchProductViewModel = hiltViewModel()
) {
    remember {
        viewModel.loadList()
    }
    val data by remember { viewModel.productList }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    val searchWidgetState = remember {
        mutableStateOf(SearchWidgetState.OPENED)
    }

    val searchTextState = remember {
        mutableStateOf(value = "")
    }

    Column() {
        SearchTopBar(
            navController,
            title = "Ürün Arama",
            textSearch = "Ara",
            searchWidgetState = searchWidgetState.value,
            searchTextState = searchTextState.value,
            onTextChange = {
                searchTextState.value = it
            },
            onCloseClicked = {
                searchWidgetState.value = SearchWidgetState.CLOSED
            },
            onSearchClicked = {
                viewModel.searchData(it)
            },
            onSearchTriggered = {
                searchWidgetState.value = SearchWidgetState.OPENED
            }
        )
        ErrorControllerBasicComponent(loading, error, onClick = { viewModel.loadList() }) {
            ProductListView(data, navController, false)
        }
    }
}
