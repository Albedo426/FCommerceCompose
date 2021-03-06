package com.mobilist.fcommercecompose.ui.components.text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mobilist.fcommercecompose.R

@Composable
fun BasicOutlinedText(
    myIncluder: String = "",
    modifier: Modifier = Modifier,
    text: String,
    line: Int = 1,
    border: Int = 25,
    textChange: (String) -> Unit = {}
) {
    var myText by remember { mutableStateOf(myIncluder) }
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it; textChange(myText) },
        maxLines = line,
        // label = { Text(text,color = colorResource(R.color.text_color)) },
        label = { Text(text, color = colorResource(R.color.text_colorDark)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(border), // 50
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(R.color.mainBlue),
            unfocusedBorderColor = colorResource(R.color.mainBlue)
        ),

    )
}
