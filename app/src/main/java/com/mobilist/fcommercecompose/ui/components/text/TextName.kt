package com.mobilist.fcommercecompose.ui.components.text

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mobilist.fcommercecompose.R

@Composable
fun TextName(
    modifier: Modifier,
    text: String,
    border: Int = 25,
    textChange: (String) -> Unit = {}
) {
    var name by rememberSaveable { mutableStateOf("") }
    TextField(
        value = name,
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(R.color.text_colorDark),
            disabledTextColor = Color.Transparent,
            backgroundColor = colorResource(R.color.white),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        onValueChange = { name = it; textChange(name) },
        // label = { Text(text,color = colorResource(R.color.text_color)) },
        placeholder = { Text(text, color = colorResource(R.color.text_colorDark)) },
        modifier = modifier.border(
            width = 2.dp,
            color = colorResource(R.color.text_border_color),
            shape = RoundedCornerShape(border)
        ),
        shape = RoundedCornerShape(border), // 50
    )
}
