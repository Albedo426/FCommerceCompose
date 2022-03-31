package com.mobilist.fcommercecompose.ui.product_detail.components.product_comments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilist.fcommercecompose.data.model.CommentDetailModel
import com.mobilist.fcommercecompose.ui.product_detail.components.rading_bar.RatingBar

@ExperimentalComposeUiApi
@Composable
fun CommentItem(item: CommentDetailModel) {

    RatingBar(
        rating = item.scorePoint,
        modifier = Modifier.width(13.dp),
        clickable = false,
        animate = false
    )
    Row() {
        Text(
            text = item.name + " " + item.lastName,
            modifier = Modifier.alpha(0.7f),
            fontSize = 13.sp
        )
        Text(text = " " + item.date, modifier = Modifier.alpha(0.5f), fontSize = 13.sp)
    }
    Text(text = item.commentText, fontSize = 13.sp)
}
