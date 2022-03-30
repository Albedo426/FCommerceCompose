package com.mobilist.fcommercecompose.ui.components.dialog


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mobilist.fcommercecompose.R

@Composable
fun CustomDialog(
    openDialog: MutableState<Boolean>,
    title: String = "",
    text: String = "",
    acceptText: String = "",
    denyText: String = "",
    Image: Int=0,
    customDialogReturn: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color =colorResource(R.color.background_color)
                    .copy(alpha = 0.6f)
            ),
        contentAlignment = Alignment.Center
    ) {
        CustomDialogUI(
            openDialogCustom = openDialog,
            title = title,
            text = text,
            acceptText = acceptText,
            denyText = denyText,
            Image = Image
        ) {
            customDialogReturn(it)
        }
    }

}

@Composable
fun CustomDialogUI(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    acceptText: String,
    denyText: String,
    Image: Int,
    openDialogCustom: MutableState<Boolean>,
    customDialogReturn: (Boolean) -> Unit = {}
) {
    val modifierClickable=if(acceptText=="" && denyText==""){
        Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    openDialogCustom.value = false
                }
            )
    }else{
        Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
    }
    Card(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(10.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = modifierClickable,
        elevation = 8.dp
    ) {
        Column(
            modifier
                .background(colorResource(R.color.background_color))
        ) {
            //.......................................................................
            if(Image!=0){
                Image(
                painter = painterResource(Image),
                contentDescription = null, // decorative
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = colorResource(R.color.mainBlue)
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                if (title != "") {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        color=colorResource(R.color.text_colorDark),
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h4,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (text != "") {
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        color=colorResource(R.color.text_colorDark),
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h5
                    )
                }

            }
            //.......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(color = colorResource(R.color.text_border_color)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                if (denyText != "") {
                    TextButton(onClick = {
                        openDialogCustom.value = false
                        customDialogReturn(false)
                        println("denyText")
                    }) {
                        Text(
                            denyText,
                            fontWeight = FontWeight.Bold,
                            color=colorResource(R.color.text_colorDark),
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
                if (acceptText != "") {
                    TextButton(onClick = {
                        openDialogCustom.value = false
                        customDialogReturn(true)
                        println(openDialogCustom.value)
                    }) {
                        Text(
                            acceptText,
                            fontWeight = FontWeight.ExtraBold,
                            color=colorResource(R.color.text_colorDark),
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
            }
        }
    }
}

