package com.mobilist.fcommercecompose.ui.main_avtivity

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.search_category_product_screen.SearchCategoryProductScreen
import com.mobilist.fcommercecompose.ui.favorite_screen.search_favorite_screen.SearchFavoriteScreen
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.search_product_screen.SearchProductScreen
import com.mobilist.fcommercecompose.ui.login_screen.LoginScreen
import com.mobilist.fcommercecompose.ui.main_screen.MainScreen
import com.mobilist.fcommercecompose.ui.order_detail_screen.OrderDetailScreen
import com.mobilist.fcommercecompose.ui.order_screen.OrderScreen
import com.mobilist.fcommercecompose.ui.product_detail.ProductDetail
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.AddressChangeScreen
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.address_add_screen.AddressAddScreen
import com.mobilist.fcommercecompose.ui.register_screen.RegisterScreen
import com.mobilist.fcommercecompose.ui.splah_screen.SplashScreen


@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
// burası main kısmı bottom olmayan salt sayfalar açar
fun NavigationMainActivity(navController: NavHostController) {//  navcontrollerMain bu
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("login_screen") {
            LoginScreen(navController = navController)
        }
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("register_screen") {
            RegisterScreen(navController = navController)
        }

        composable("address_change_screen") {
            AddressChangeScreen(navController)
        }
        composable("address_add_screen") {
            AddressAddScreen(navController)
        }
        composable("detail_product_screen/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id",0)
            }
            ProductDetail(navController = navController,Id= id!!)
        }
        composable("shopping_list_screen/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id",0)
            }
            OrderScreen(navController = navController,id!!)
        }


        composable("shopping_detail_list_screen",) {
            OrderDetailScreen(navController = navController)
        }
        composable("main_screen") {
            MainScreen(navController = navController)
        }
        composable("search_product_screen") {
            SearchProductScreen(navController = navController)
        }
        composable("search_favorite_screen") {
            SearchFavoriteScreen(navController = navController)
        }
        composable("search_category_screen/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id",0)
            }
            SearchCategoryProductScreen(navController = navController,id!!)
        }
    }
}