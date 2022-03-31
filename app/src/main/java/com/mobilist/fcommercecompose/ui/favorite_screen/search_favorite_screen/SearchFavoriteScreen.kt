package com.mobilist.fcommercecompose.ui.favorite_screen.search_favorite_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.ui.components.cards.ProductListView
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.SearchTopBar
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component.MainContentAddressView
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component.TopBar

@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun SearchFavoriteScreen(
    navController: NavHostController,
    viewModel: SearchFavoriteViewModel = hiltViewModel()
) {
    val data by remember { viewModel.productList }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    remember {
        viewModel.init()
    }
    val searchWidgetState = remember {
        mutableStateOf(SearchWidgetState.OPENED)
    }

    val searchTextState = remember {
        mutableStateOf(value = "")
    }

    Column() {
        SearchTopBar(
            navController,
            title = "Favori Ürünleri Arama",
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
        ErrorControllerBasicComponent(loading, error, onClick = {  viewModel.init()}) {
            ProductListView(data, navController)
        }
    }
}
