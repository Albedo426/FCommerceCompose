package com.mobilist.fcommercecompose.ui.components.error_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorControllerBasicComponent(
    loading: Boolean,
    error: String,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
//
    if (error != "" || loading) {
        ErrorBasicComponent(loading, error) {
            onClick()
        }
    } else {
        val contentAlpha = LocalContentAlpha.current
        CompositionLocalProvider(LocalContentAlpha provides contentAlpha, content = content)
    }
}

@Composable
fun ErrorControllerErrorOnlyTextComponent(
    loading: Boolean,
    error: String,
    content: @Composable () -> Unit
) {
//
    if (error != "" || loading) {
        ErrorOnlyTextComponent(loading, error)
    } else {
        val contentAlpha = LocalContentAlpha.current
        CompositionLocalProvider(LocalContentAlpha provides contentAlpha, content = content)
    }
}

@Composable
fun ErrorBasicComponent(loading: Boolean, error: String, onRetry: (String) -> Unit) {
    // ekranın tam ortasına koyabilirim
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
        } else if (error.isNotEmpty()) {
            ErrorView(error)
        }
    }
}

@Composable
fun ErrorView(message: String) {
    Column {
        Text(text = message, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ErrorButtonView(message: String, onRetry: (String) -> Unit) {
    Column {
        Text(text = message, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { onRetry(message) }, Modifier.align(Alignment.CenterHorizontally)) {
            Text("Tekrar Dene ")
        }
    }
}
