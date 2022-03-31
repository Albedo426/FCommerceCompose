package com.mobilist.fcommercecompose.ui.categories_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.data.model.SearchWidgetState
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.CategoryProductScreen
import com.mobilist.fcommercecompose.ui.categories_screen.lower_main_category.LowerMainCategoryScreen
import com.mobilist.fcommercecompose.ui.categories_screen.lower_simple_category.LowerSimpleCategoryScreen
import com.mobilist.fcommercecompose.ui.components.cards.BasicCard
import com.mobilist.fcommercecompose.ui.components.cards.ClickableCategoryCard
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.SearchTopBar

@ExperimentalFoundationApi
@Composable
fun CategoryScreen(
    navControllerMain: NavController,
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "category_screen") {
        composable("category_screen") {
            MyCategoryScreen(navController)
        }

        composable(
            "lower_main_category/{Id}",
            arguments = listOf(
                navArgument("Id") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = remember {
                it.arguments?.getInt("Id", 0)
            }
            LowerMainCategoryScreen(navController = navController, mainCategoryId = id!!)
        }

        composable(
            "lower_simple_category/{Id}",
            arguments = listOf(
                navArgument("Id") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = remember {
                it.arguments?.getInt("Id", 0)
            }
            LowerSimpleCategoryScreen(navController = navController, lowerCategoryId = id!!)
        }

        composable(
            "category_product_screen/{Id}",
            arguments = listOf(
                navArgument("Id") {
                    type = NavType.IntType
                }
            )
        ) {
            val id = remember {
                it.arguments?.getInt("Id", 0)
            }
            CategoryProductScreen(navController, navControllerMain, id!!)
        }
    }
}

@Composable
fun MyCategoryScreen(
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
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
                viewModel.searchCategoryMain(it)
            },
            onSearchTriggered = {
                searchWidgetState.value = SearchWidgetState.OPENED
            }
        )
        ErrorControllerErrorOnlyTextComponent(loading, error) {
            BasicCard() {
                LazyColumn(Modifier.weight(0.1f)) {
                    items(data) {
                        MainContentCategoryItem(it, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContentCategoryItem(category: Category, navController: NavHostController) {
    ClickableCategoryCard(
        click = { navController.navigate("lower_main_category/${category.UUID}") },
        string = category.name
    )
}
