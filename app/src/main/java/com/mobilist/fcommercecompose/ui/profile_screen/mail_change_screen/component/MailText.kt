package com.mobilist.fcommercecompose.ui.profile_screen.mail_change_screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mobilist.fcommercecompose.R

@Composable
fun MailText(
    modifier: Modifier = Modifier,
    text: String,
    border: Int = 25,
    textChange: (String) -> Unit = {}
) {
    var mail by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = mail,
        onValueChange = { mail = it;textChange(mail) },
        maxLines = 1,
        singleLine = true,
        // label = { Text(text,color = colorResource(R.color.text_color)) },
        label = { Text(text, color = colorResource(R.color.text_colorDark)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(border),//50
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(R.color.mainBlue),
            unfocusedBorderColor = colorResource(R.color.mainBlue)),
        )
}
