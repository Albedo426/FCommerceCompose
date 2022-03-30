package com.mobilist.fcommercecompose.ui.categories_screen.lower_main_category

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.ui.categories_screen.MainContentCategoryItem
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.components.top_bar.SearchTopBar


@SuppressLint("RememberReturnType")
@Composable
fun LowerMainCategoryScreen(
    navController: NavHostController,
    mainCategoryId: Int,
    viewModel: LowerMainCategoryViewModel = hiltViewModel()
) {
    remember{
        viewModel.init(mainCategoryId)
    }
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
            title= "Kategoriler",
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
                viewModel.searchCategoriesLowerMainProduct(mainCategoryId,it)
            },
            onSearchTriggered = {
                searchWidgetState.value = SearchWidgetState.OPENED
            }
        )
        if (error != "" || loading) {
            ErrorOnlyTextComponent(loading, error)
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Card(
                    elevation = 4.dp,
                ) {
                    LazyColumn(Modifier.weight(0.1f)) {
                        items(data) {
                            MainContentLowerMainCategoryScreen(it,navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainContentLowerMainCategoryScreen(category: Category, navController: NavHostController,) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {navController.navigate("lower_simple_category/${category.UUID}")}) {
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.Start
        ) {
            Text(
                category.name, modifier = Modifier
                    .padding(10.dp, 20.dp, 20.dp, 20.dp)
            )
        }
    }

}