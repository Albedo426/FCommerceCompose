package com.mobilist.fcommercecompose.ui.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.HomeProductScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController,navControllerMain: NavController) {
      HomeProductScreen(navController =navController, navControllerMain =navControllerMain )
}
