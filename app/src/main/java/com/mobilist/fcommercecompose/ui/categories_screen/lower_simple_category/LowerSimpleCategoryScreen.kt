package com.mobilist.fcommercecompose.ui.categories_screen.lower_simple_category

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.ui.categories_screen.lower_main_category.MainContentLowerMainCategoryScreen
import com.mobilist.fcommercecompose.ui.components.cards.BasicCard
import com.mobilist.fcommercecompose.ui.components.cards.ClickableCategoryCard
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.SearchTopBar

@SuppressLint("RememberReturnType")
@Composable
fun LowerSimpleCategoryScreen(
    navController: NavHostController,
    lowerCategoryId: Int,
    viewModel: LowerSimpleCategoryViewModel = hiltViewModel()
) {
    remember {
        viewModel.init(lowerCategoryId)
    }
    val data by remember { viewModel.listCategory }
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
            title = "Kategoriler",
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
                viewModel.searchCategoriesLowerSimpleProduct(lowerCategoryId, it)
            },
            onSearchTriggered = {
                searchWidgetState.value = SearchWidgetState.OPENED
            }
        )
        ErrorControllerErrorOnlyTextComponent(loading, error) {
            BasicCard() {
                LazyColumn(Modifier.weight(0.1f)) {
                    items(data) {
                        MainContentLowerSimpleCategoryScreen(it, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContentLowerSimpleCategoryScreen(category: Category, navController: NavHostController) {
    ClickableCategoryCard(
        click = { navController.navigate("category_product_screen/${category.UUID}") },
        string = category.name
    )
}
