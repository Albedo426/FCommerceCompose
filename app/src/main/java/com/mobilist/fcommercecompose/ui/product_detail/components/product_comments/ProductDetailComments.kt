package com.mobilist.fcommercecompose.ui.product_detail.components.product_comments

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilist.fcommercecompose.data.model.CommentDetailModel
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal

@ExperimentalComposeUiApi
@Composable
fun ProductDetailComments(list: List<CommentDetailModel>) {
    Column(modifier = Modifier.padding(5.dp)) {
        for (data in list) {
            CommentItem(data)
            MySpacerHorizontal()
        }
    }

}
