package com.mobilist.fcommercecompose.ui.components.button

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.MyLikeButtonViewModel

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
        modifier = modifier, // avoid the oval shape
        shape = RoundedCornerShape(25),
        contentPadding = PaddingValues(0.dp), // avoid the little icon
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(text = text, color = colorResource(R.color.text_color), modifier = Modifier.padding(vertical = 12.dp))
    }
}

@Composable
fun MyExtendedFloatingActionButton(text: String = "Onayla", imageVector: ImageVector = Icons.Filled.Add, onClick: () -> Unit = {}) {
    ExtendedFloatingActionButton(
        text = { Text(text = text, color = Color.White) },
        icon = { Icon(imageVector, "", tint = Color.White) },
        backgroundColor = colorResource(R.color.mainBlue),
        onClick = {
            onClick()
        }
    )
}
@SuppressLint("RememberReturnType")
@Composable
fun MyLikeButton(modifier: Modifier, booleanClick: MutableState<Boolean>, Id: Int, myLikeButtonViewModel: MyLikeButtonViewModel = hiltViewModel()) {
    Card(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                booleanClick.value = !booleanClick.value
                myLikeButtonViewModel.likeClick(Id)
                //  navController.navigate("crypto_detail_screen/${cryptoListItem.currency}/${cryptoListItem.price}")
            }
    ) {
        if (booleanClick.value) {
            Image(
                painterResource(R.drawable.ic_baseline_item_favorite_24),
                contentDescription = "", modifier = Modifier.padding(3.dp), colorFilter = ColorFilter.tint(Color.Red)
            )
        } else {
            Image(
                painterResource(R.drawable.ic_baseline_favorite_border_24),
                contentDescription = "", modifier = Modifier.padding(3.dp)
            )
        }
    }
}
