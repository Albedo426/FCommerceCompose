package com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerBasicComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.component.TextPasswordOutlined

@Composable
fun PasswordChangeScreen(
    navController: NavHostController,
    viewModel: PasswordChangeViewModel = hiltViewModel()
) {

    var error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    ErrorControllerBasicComponent(loading, error, onClick = { error = "" }) {
        MainContentPasswordChangeScreen(navController, viewModel)
    }
}

@Composable
fun MainContentPasswordChangeScreen(
    navController: NavHostController,
    viewModel: PasswordChangeViewModel
) {
    val openDialog = remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        BasicTopBar("Şifre Değişikliği")
        LazyColumn(Modifier.weight(0.1f)) {
            item {
                PasswordScreenAttr(openDialog, viewModel)
            }
        }
    }
    if (openDialog.value) {
        // openDialog, openDialog, editMessage
        CustomDialog(
            openDialog = openDialog,
            text = "Güncelleme İşlemi Başarılı",
            acceptText = "Tamam",
            Image = R.drawable.ic_baseline_success_24
        ) {
            navController.navigate("profile_screen")
        }
    }
}

@Composable
fun PasswordScreenAttr(openDialog: MutableState<Boolean>, viewModel: PasswordChangeViewModel) {
    var tempPassword by remember { mutableStateOf("") }
    var tempPassword2 by remember { mutableStateOf("") }
    var mainPass by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Card(
            elevation = 4.dp,
        ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                TextPasswordOutlined(text = "Mevcut Şifre") {
                    mainPass = it
                }
                TextPasswordOutlined(text = "Yeni Şifre") {
                    tempPassword = it
                }
                TextPasswordOutlined(text = "Tekrar Yeni Şifre") {
                    tempPassword2 = it
                }
                BasicButton(text = "Güncelle") {
                    if (tempPassword == tempPassword2) {
                        viewModel.changeUserPassword(openDialog, mainPass, tempPassword)
                    }
                }
            }
        }
    }
}
