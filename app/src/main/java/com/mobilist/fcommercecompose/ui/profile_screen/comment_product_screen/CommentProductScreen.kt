package com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.SearchTopBar
import com.mobilist.fcommercecompose.ui.product_detail.Content
import com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen.component.ProductCommentItem

@ExperimentalFoundationApi
@Composable
fun CommentProductScreen(navController: NavController, viewModel: CommentProductViewModel = hiltViewModel()) {
    val data by remember { viewModel.list }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    val searchWidgetState = remember {
        mutableStateOf(SearchWidgetState.CLOSED)
    }

    val searchTextState = remember {
        mutableStateOf(value = "")
    }
    Column() {
        SearchTopBar(
            navController,
            title = "Ürün Ara",
            textSearch = "Ara",
            searchWidgetState = searchWidgetState.value,
            searchTextState = searchTextState.value,
            onTextChange = {
                searchTextState.value = it
            },
            onCloseClicked = {
                searchWidgetState.value = SearchWidgetState.CLOSED
                viewModel.searchCategoryMain("")
            },
            onSearchClicked = {
                viewModel.searchCategoryMain(it)
            },
            onSearchTriggered = {
                searchWidgetState.value = SearchWidgetState.OPENED
            }
        )
        ErrorControllerErrorOnlyTextComponent(loading, error) {
            LazyColumn(
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                items(data) {
                    ProductCommentItem(navController, it)
                }
            }
        }
    }
}
