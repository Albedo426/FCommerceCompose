package com.mobilist.fcommercecompose.ui.components.text

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mobilist.fcommercecompose.R

@Composable
fun TextPassword(
    modifier: Modifier,
    text: String,
    border: Int = 25,
    textChange: (String) -> Unit = {}
) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        maxLines = 1,
        singleLine = true,
        value = password,
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(R.color.text_color),
            disabledTextColor = Color.Transparent,
            backgroundColor = colorResource(R.color.white),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        onValueChange = { password = it;textChange(password) },
        // label = { Text(text,color = colorResource(R.color.text_color)) },
        placeholder = { Text(text, color = colorResource(R.color.text_colorDark)) },
        modifier = modifier.border(
            width = 2.dp,
            color = colorResource(R.color.text_border_color),
            shape = RoundedCornerShape(border)
        ),
        shape = RoundedCornerShape(border),//50
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisibility)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(imageVector = image, "")
            }
        }
    )
}