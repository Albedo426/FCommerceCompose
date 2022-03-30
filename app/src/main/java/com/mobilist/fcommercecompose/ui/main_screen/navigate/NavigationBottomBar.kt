package com.mobilist.fcommercecompose.ui.main_screen.navigate

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mobilist.fcommercecompose.ui.categories_screen.CategoryScreen
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.CategoryProductScreen
import com.mobilist.fcommercecompose.ui.categories_screen.lower_main_category.LowerMainCategoryScreen
import com.mobilist.fcommercecompose.ui.categories_screen.lower_simple_category.LowerSimpleCategoryScreen
import com.mobilist.fcommercecompose.ui.favorite_screen.FavoriteScreen
import com.mobilist.fcommercecompose.ui.home_screen.HomeScreen
import com.mobilist.fcommercecompose.ui.main_screen.bottom_navigate_bar.NavigationItem
import com.mobilist.fcommercecompose.ui.order_screen.OrderScreen
import com.mobilist.fcommercecompose.ui.product_detail.ProductDetail
import com.mobilist.fcommercecompose.ui.profile_screen.ProfileScreen
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.AddressChangeScreen
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.address_add_screen.AddressAddScreen
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.InComingOrderScreen
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen.ChangeOrderStatusScreen
import com.mobilist.fcommercecompose.ui.profile_screen.mail_change_screen.MailChangeScreen
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.MyOrderScreen
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.my_order_detail.MyOrderDetailScreen
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.PasswordChangeScreen
import com.mobilist.fcommercecompose.ui.profile_screen.user_change_screen.UserChangeScreen

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
//burası bottombar için  ordan tıklananları açar ekleme çıkarma olanbilir
//bottom bar ile birlikte gider buttom bar altta kalmasını istiyorsan burdan işlem yap
fun NavigationBottomBar(navController: NavHostController, navControllerMain: NavController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController,navControllerMain)
        }
        composable(NavigationItem.Shopping.route) {
            OrderScreen(navControllerMain)
        }
        composable(NavigationItem.Favorite.route) {
            FavoriteScreen(navController,navControllerMain)
        }
        composable(NavigationItem.Category.route) {
            CategoryScreen(navControllerMain)
        }
        composable(NavigationItem.Account.route) {
            ProfileScreen(navControllerMain)
        }
        composable("password_change_screen") {
            PasswordChangeScreen(navController)
        }
        composable("mail_change_screen") {
            MailChangeScreen(navController)
        }
        composable("address_change_screen") {
            AddressChangeScreen(navController)
        }
        composable("address_add_screen") {
            AddressAddScreen(navController)
        }
        composable("user_change_screen") {
            UserChangeScreen(navController)
        }
        composable("my_order_screen") {
            MyOrderScreen(navController)
        }
        composable("in_coming_order_screen") {
            InComingOrderScreen(navController)
        }
        composable("change_order_status_screen/{Id}", arguments = listOf(
        navArgument("Id") {
            type = NavType.IntType
        }
        )) {
        val id = remember {
            it.arguments?.getInt("Id",0)
        }
            ChangeOrderStatusScreen(navController = navController,Id= id!!)
        }

        composable("my_order_detail_screen/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id",0)
            }
            MyOrderDetailScreen(navController = navController,Id= id!!)
        }


        composable("lower_main_category/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id",0)
            }
            LowerMainCategoryScreen(navController = navController,mainCategoryId= id!!)
        }

        composable("lower_simple_category/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id",0)
            }
            LowerSimpleCategoryScreen(navController = navController,lowerCategoryId= id!!)
        }

        composable("category_product_screen/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id",0)
            }
            CategoryProductScreen(navController,navControllerMain,id!!)
        }
    }
}
