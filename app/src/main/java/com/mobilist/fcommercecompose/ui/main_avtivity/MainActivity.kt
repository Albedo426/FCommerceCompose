package com.mobilist.fcommercecompose.ui.main_avtivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.theme.FCommerceComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FCommerceComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = colorResource(R.color.background_color),
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavigationMainActivity(navController)
                }
            }
        }
    }
}
