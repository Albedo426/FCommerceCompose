package com.mobilist.fcommercecompose.ui.favorite_screen.component.list_view

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.data.model.ProductMainItemModel
import com.mobilist.fcommercecompose.ui.categories_screen.category_product.CategoryProductViewModel
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.favorite_screen.FavoriteViewModel
import com.mobilist.fcommercecompose.ui.home_screen.home_product_screen.component.list_view.ItemProductHome


@SuppressLint("RememberReturnType")
@ExperimentalFoundationApi
@Composable
fun ProductListView(item: List<ProductMainItemModel>, navController: NavController,isSearch:Boolean=true) {
    val modifier=if(isSearch){
        Modifier.padding(bottom=50.dp)
    }
    else{
        Modifier
    }
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier= modifier
    ) {
        items(item) {
            ItemProductHome(navController,it)
        }
    }
}
