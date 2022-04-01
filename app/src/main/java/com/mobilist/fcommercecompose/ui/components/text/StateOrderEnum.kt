package com.mobilist.fcommercecompose.ui.components.text

import androidx.compose.runtime.Composable
import com.mobilist.fcommercecompose.data.entity.sales.StateOrder

@Composable
fun StateOrderEnum(
    index: Int,
    content: @Composable (item: String) -> Unit
) {
    StateOrder.values()
        .firstOrNull { it.ordinal == index }?.name?.let { item ->
            content(item)
        }
}
