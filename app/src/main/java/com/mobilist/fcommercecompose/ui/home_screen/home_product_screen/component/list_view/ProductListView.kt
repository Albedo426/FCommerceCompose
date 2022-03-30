package com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.component.list_view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel


@ExperimentalFoundationApi
@Composable
fun ProductListView(item: List<ProductMainItemModel>, navController: NavController,ifSearch:Boolean=true) {
    val modifier=if(ifSearch){
        Modifier.padding(bottom=50.dp)
    }
    else{
        Modifier
    }
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier=modifier
    ) {
        items(item) {
            ItemProductHome(navController,it)
        }
    }
}


