package com.mobilist.fcommercecompose.ui.order_detail_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mobilist.fcommercecompose.R

@Composable
fun OrderDetailOutlinedTextField(title: String, str: MutableState<String>, modifier: Modifier) {
    OutlinedTextField(
        singleLine = true,
        value = str.value,
        onValueChange = { str.value = it },
        modifier = modifier
           ,
        label = { Text(title) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.text_colorDark),
            unfocusedBorderColor = colorResource(id = R.color.text_colorDark),
            focusedLabelColor = colorResource(id = R.color.text_colorDark),
            cursorColor = colorResource(id = R.color.text_colorDark),
            textColor = colorResource(id = R.color.text_colorDark)
        )
    )
}
