package com.mobilist.fcommercecompose.ui.categories_screen.category_product.search_category_product_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.CategoryProductViewModel
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.component.list_view.ProductListView
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.DefaultAppBar
import com.mobilist.fcommercecompose.ui.components.top_bar.SearchTopBar

@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun SearchCategoryProductScreen(
    navController: NavHostController,
    Id:Int,viewModel: SearchCategoryProductViewModel = hiltViewModel()
) {
    val data by remember {viewModel.productList}
    val error by remember {viewModel.errorMessage}
    val loading by remember {viewModel.isLoading}
    val searchWidgetState = remember {
        mutableStateOf(SearchWidgetState.OPENED)
    }

    val searchTextState = remember {
        mutableStateOf(value = "")
    }
    remember{
        viewModel.init(Id)
    }
    Column() {
        SearchTopBar(
            navController,
            title= "Kategoriye Göre Ürün Arama",
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
                viewModel.searchData(Id,it)
            },
            onSearchTriggered = {
                searchWidgetState.value = SearchWidgetState.OPENED
            }
        )
        if (error != "" || loading) {
            ErrorBasicComponent(loading = loading, error =error){
                viewModel.init(Id)
            }
        } else {
            ProductListView(data,navController,false)
        }
    }
}