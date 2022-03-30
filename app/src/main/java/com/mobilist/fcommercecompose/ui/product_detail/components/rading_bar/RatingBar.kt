package com.mobilist.fcommercecompose.ui.product_detail.components.rading_bar

import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@ExperimentalComposeUiApi
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int,
    clickable:Boolean=true,
    animate:Boolean=true
) {
    var ratingState by remember {
        mutableStateOf(rating)
    }

    var selected by remember {
        mutableStateOf(false)
    }

    val size by animateDpAsState(
        targetValue=if(animate){
              if (selected) 30.dp else 15.dp
        }else{
             if (selected) 15.dp else 15.dp
        },spring(Spring.DampingRatioMediumBouncy)
    )

    Row(
        modifier = Modifier.wrapContentWidth()
            .padding(3.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = com.mobilist.fcommercecompose.R.drawable.ic_baseline_star_rate_24),
                contentDescription = "star",
                modifier = modifier
                    .width(size)
                    .height(size)
                    .pointerInteropFilter {
                        if(clickable){
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> {
                                    selected = true
                                    ratingState = i
                                }
                                MotionEvent.ACTION_UP -> {
                                    selected = false
                                }
                            }
                        }
                        true
                    },
                tint = if (i <= ratingState) Color(0xFFFFD700) else Color(0xFFA2ADB1)
            )
        }
    }
}