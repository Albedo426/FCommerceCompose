package com.mobilist.fcommercecompose.ui.order_screen

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.order_screen.component.OrderItem

@SuppressLint("RememberReturnType")
@Composable
fun OrderScreen(
    navController: NavController,
    Id: Int = 0,
    viewModel: OrderScreenViewModel = hiltViewModel(),
) {
    remember{
        viewModel.loadShoppingList()
    }
    val data by remember {viewModel.list}
    val price by remember {viewModel.allPrice}
    var error by remember {viewModel.errorMessage}
    val loading by remember {viewModel.isLoading}
    Log.e("TAG",data.size.toString() )
    Column(Modifier.fillMaxSize()) {
        if(error!=""){
            ErrorOnlyTextComponent(loading, error)
        }else{
            BasicTopBar("Sepetim - ${data.size} Ürün Var")
            LazyColumn(Modifier.weight(0.1f)) {
                items(data) {
                    OrderItem(it)
                }
            }
            val modifier = if(Id==0){
                Modifier
                    .padding(0.dp)
                    .background(color = Color.White)
                    .padding(5.dp)
                    .fillMaxWidth()
                    .padding(bottom = 60.dp)
            }else{
                Modifier
                    .padding(0.dp)
                    .background(color = Color.White)
                    .padding(5.dp)
                    .fillMaxWidth()
            }
            Row(
                modifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(text = "Toplam")
                    Text(text = "%.2f".format(price) )
                }
                ExtendedFloatingActionButton(
                    text = { Text(text = "Sepeti Onayla", color = Color.White) },
                    icon = { Icon(Icons.Filled.Add, "", tint = Color.White) },
                    backgroundColor = colorResource(R.color.mainBlue),
                    onClick = {navController.navigate("shopping_detail_list_screen")})
            }
        }
    }

}
