package com.mobilist.fcommercecompose.ui.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mobilist.fcommercecompose.R

@Composable
fun BasicButton(
    text: String,
    color: Color = colorResource(R.color.mainBlue),
    modifier: Modifier = Modifier
        .padding(vertical = 20.dp)
        .padding(5.dp)
        .fillMaxWidth(),
    onClick: (String) -> Unit = {}
) {
    Button(
        onClick = { onClick(text) },
        modifier = modifier, //avoid the oval shape
        shape = RoundedCornerShape(25),
        contentPadding = PaddingValues(0.dp),  //avoid the little icon
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(text = text, color = colorResource(R.color.text_color), modifier = Modifier.padding(vertical = 12.dp))
    }

}