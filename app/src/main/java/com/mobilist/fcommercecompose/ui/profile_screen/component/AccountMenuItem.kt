package com.mobilist.fcommercecompose.ui.profile_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mobilist.fcommercecompose.R

@Composable
fun AccountMenuItem(mainText: String, secText: Int = R.drawable.ic_baseline_remove_24, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(mainText) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            if (secText != 0) {
                Icon(
                    painterResource(secText), "",
                    modifier = Modifier
                        .padding(20.dp)
                )
            }
            Text(
                mainText,
                modifier = Modifier
                    .padding(10.dp, 20.dp, 20.dp, 20.dp)
            )
        }
    }
}
