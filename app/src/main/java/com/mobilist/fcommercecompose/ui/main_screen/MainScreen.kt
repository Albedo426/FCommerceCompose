package com.mobilist.fcommercecompose.ui.main_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.main_screen.bottom_navigate_bar.BottomNavigationBar
import com.mobilist.fcommercecompose.ui.main_screen.navigate.NavigationBottomBar

@ExperimentalComposeUiApi
@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navControllerMain = rememberNavController()
    Scaffold(
        // topBar={ TopBar() },
        bottomBar = { BottomNavigationBar(navControllerMain) }
    ) {
        Surface(color = colorResource(R.color.background_color), modifier = Modifier.fillMaxSize()) {
            NavigationBottomBar(navControllerMain, navController)
        }
    }
}
