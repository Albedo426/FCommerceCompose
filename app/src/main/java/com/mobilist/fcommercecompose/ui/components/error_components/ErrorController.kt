package com.mobilist.fcommercecompose.ui.components.error_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorBasicComponent(loading: Boolean, error: String,onRetry: (String) -> Unit) {
    //ekranın tam ortasına koyabilirim
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (loading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if (error.isNotEmpty()) {
            ErrorButtonView(error) {
                onRetry(it)
            }
        }
    }
}

@Composable
fun ErrorOnlyTextComponent(loading: Boolean, error: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (loading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        else if (error.isNotEmpty()) {
            ErrorView(error)
        }
    }
}


@Composable
fun ErrorView(message:String) {
    Column {
        Text(text = message, color = Color.Red,fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ErrorButtonView(message:String, onRetry: (String) -> Unit) {
    Column {
        Text(text = message, color = Color.Red,fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { onRetry(message)},Modifier.align(Alignment.CenterHorizontally)) {
            Text("Tekrar Dene ")
        }
    }
}