package com.mobilist.fcommercecompose.ui.components.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TextMultiMore(vararg str: String) {
    str.forEach {
        Text(
            text = it,
        )
    }
}
