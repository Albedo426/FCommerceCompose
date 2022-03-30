package com.mobilist.fcommercecompose.ui.login_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.text.TextName
import com.mobilist.fcommercecompose.ui.components.text.TextPassword

@Composable
fun LoginScreen(
    navController: NavController,
    viewModelComposable: LoginViewModel = hiltViewModel()
) {
    val viewModel by remember {
        mutableStateOf(viewModelComposable)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Giriş yap",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp, color = colorResource(R.color.mainBlue)
            )
            Spacer(modifier = Modifier.height(50.dp))
            TextName(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                "Kullanıcı Adı"
            ) {
                viewModel.loginModel.userEmail = it
            }
            TextPassword(
                Modifier.fillMaxWidth(), "Password"
            ) {
                viewModel.loginModel.userPassword = it
            }
            BasicButton("Giriş Yap"){
                viewModel.login()
            }
            Text(
                text = "Bir hesabın yok mu? Kaydol.",
                color = colorResource(R.color.text_color),
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clickable { navController.navigate("register_screen") },
            )
        }
        if (viewModel.isLoading.value) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (viewModel.succses.value) {
            navController.navigate("main_screen") {
                popUpTo("login_screen") {
                    inclusive = true
                }
            }
        }
        if (viewModel.errorMessage.value != "") {
            Toast.makeText(LocalContext.current, "Kullanıcı Bulunamadı", Toast.LENGTH_SHORT).show()
            viewModel.errorMessage.value = ""
        }
    }


}

