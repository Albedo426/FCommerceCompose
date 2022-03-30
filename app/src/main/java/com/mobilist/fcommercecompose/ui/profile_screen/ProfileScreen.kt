package com.mobilist.fcommercecompose.ui.profile_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.CategoryProductScreen
import com.mobilist.fcommercecompose.ui.categories_screen.lower_main_category.LowerMainCategoryScreen
import com.mobilist.fcommercecompose.ui.categories_screen.lower_simple_category.LowerSimpleCategoryScreen
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.order_screen.component.OrderItem
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.AddressChangeScreen
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.address_add_screen.AddressAddScreen
import com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen.CommentProductScreen
import com.mobilist.fcommercecompose.ui.profile_screen.comment_product_screen.comment_add_screen.CommentAddScreen
import com.mobilist.fcommercecompose.ui.profile_screen.component.AccountMenu
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.InComingOrderScreen
import com.mobilist.fcommercecompose.ui.profile_screen.incoming_order_screen.change_order_status_screen.ChangeOrderStatusScreen
import com.mobilist.fcommercecompose.ui.profile_screen.mail_change_screen.MailChangeScreen
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.MyOrderScreen
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.my_order_detail.MyOrderDetailScreen
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.PasswordChangeScreen
import com.mobilist.fcommercecompose.ui.profile_screen.user_change_screen.UserChangeScreen

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun ProfileScreen(
    navControllerMain: NavController,
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "profile_screen") {
        composable("profile_screen") {
            MyProfileScreen(navController, navControllerMain)
        }
        composable("comment_product_screen") {
            CommentProductScreen(navController)
        }
        composable("comment_add_screen/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id", 0)
            }
            CommentAddScreen(navController = navController, Id = id!!)
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
                it.arguments?.getInt("Id", 0)
            }
            ChangeOrderStatusScreen(navController = navController, Id = id!!)
        }

        composable("my_order_detail_screen/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id", 0)
            }
            MyOrderDetailScreen(navController = navController, Id = id!!)
        }

        composable("lower_main_category/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id", 0)
            }
            LowerMainCategoryScreen(navController = navController, mainCategoryId = id!!)
        }

        composable("lower_simple_category/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id", 0)
            }
            LowerSimpleCategoryScreen(navController = navController, lowerCategoryId = id!!)
        }

        composable("category_product_screen/{Id}", arguments = listOf(
            navArgument("Id") {
                type = NavType.IntType
            }
        )) {
            val id = remember {
                it.arguments?.getInt("Id", 0)
            }
            CategoryProductScreen(navController, navControllerMain, id!!)
        }

    }
}

@Composable
fun MyProfileScreen(
    navController: NavHostController, navControllerMain: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val myUser by remember {
        viewModel.myUser
    }
    val errorMessage by remember {
        viewModel.errorMessage
    }
    val isLoading by remember {
        viewModel.isLoading
    }
    Column(Modifier.fillMaxSize()) {
        if (errorMessage != "") {
            ErrorOnlyTextComponent(isLoading, errorMessage)
        } else {
            BasicTopBar(myUser.userEmail)
            LazyColumn(
                Modifier.weight(0.1f)
            ) {
                item {
                    ProfileAttrButton(navController, navControllerMain, viewModel)
                }
            }
        }
    }

}


@Composable
fun ProfileAttrButton(
    navController: NavHostController,
    navControllerMain: NavController,
    viewModel: ProfileViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Card(
            elevation = 4.dp,
        ) {
            Column() {
                AccountMenu(navController, navControllerMain, viewModel)
            }
        }
    }
}
