package com.mobilist.fcommercecompose.ui.components.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.mobilist.fcommercecompose.R

@Composable
fun Title(mainText:String,secText:String="", onClick: (String) -> Unit = {}) {
    Row(modifier= Modifier
        .fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
        Text(mainText, fontWeight = FontWeight.W700,color = colorResource(id = R.color.text_colorDark))
        if(secText!=""){
            Text(secText, modifier= Modifier
                .alpha(0.5f)
                .clickable { onClick(secText) },color = colorResource(id = R.color.text_colorDark))
        }
    }
}