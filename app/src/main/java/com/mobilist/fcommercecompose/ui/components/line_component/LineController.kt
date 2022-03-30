package com.mobilist.fcommercecompose.ui.components.line_component

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MySpacerHorizontal(color: Color = Color.LightGray) {
    /* Spacer(
         modifier = Modifier
             .height(1.dp)
             .fillMaxWidth()
             .background(color = Color.LightGray)
     )*/
    Divider(
        color = color,
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )
}
@Composable
fun MySpacerVertical(color: Color) {
    Divider(
        color = color,
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
    )
}