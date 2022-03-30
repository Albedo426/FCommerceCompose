package com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilist.fcommercecompose.R

@Composable
fun TopBar(title:String= stringResource(R.string.app_name) ,onClick:(String)-> Unit= {}) {
    TopAppBar(
        title = { Text(text =title, fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.mainBlue),
        contentColor = Color.White,
        actions = {
            Text(text = "Ekle",modifier= Modifier.clickable { onClick(title) }.padding(end = 5.dp))
        },
    )
}