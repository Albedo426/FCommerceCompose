package com.mobilist.fcommercecompose.ui.profile_screen.mail_change_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorBasicComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.main_screen.bottom_navigate_bar.NavigationItem
import com.mobilist.fcommercecompose.ui.profile_screen.ProfileAttrButton
import com.mobilist.fcommercecompose.ui.profile_screen.mail_change_screen.component.MailText
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.MainContentPasswordChangeScreen
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.PasswordChangeViewModel
import com.mobilist.fcommercecompose.ui.profile_screen.password_change_screen.PasswordScreenAttr

@Composable
fun MailChangeScreen(
    navController: NavHostController,
    viewModel: MailChangeViewModel = hiltViewModel()
) {
    var error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    Column(Modifier.fillMaxSize()) {
        if (error != "") {
            ErrorBasicComponent(loading, error) {
                error = ""
            }
        } else {

            MainContentMailChangeScreen(navController, viewModel)
        }
    }
}


@Composable
fun MainContentMailChangeScreen(navController: NavHostController, viewModel: MailChangeViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        BasicTopBar("Eposta değişikliği")
        LazyColumn(Modifier.weight(0.1f)) {
            item {
                MailScreenAttr(openDialog, viewModel)
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
fun MailScreenAttr(openDialog: MutableState<Boolean>, viewModel: MailChangeViewModel) {
    var user by remember { viewModel.myUser }
    var tempMail by remember { mutableStateOf("") }
    var tempMail2 by remember { mutableStateOf("") }
    var mainMail by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Card(
            elevation = 4.dp,
        ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
                MailText(text = "Mevcut E-Posta") {
                    mainMail = it;
                }
                MailText(text = "Yeni  E-Posta") {
                    tempMail = it;
                }
                MailText(text = "Tekrar Yeni E-Posta") {
                    tempMail2 = it;
                }
                BasicButton(text = "Güncelle") {
                    if (tempMail == tempMail2) {
                        viewModel.updateUserMailById(openDialog, mainMail, tempMail2)
                    }
                }
            }
        }
    }
}