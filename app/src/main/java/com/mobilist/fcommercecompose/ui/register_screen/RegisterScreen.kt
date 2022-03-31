package com.mobilist.fcommercecompose.ui.register_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.model.ResponseUser
import com.mobilist.fcommercecompose.data.model.isNullData
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.text.TextName
import com.mobilist.fcommercecompose.ui.components.text.TextPassword
import com.mobilist.fcommercecompose.ui.profile_screen.user_change_screen.MainContentUserChangeScreen

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val requestUser by remember { viewModel.responseUser }
    val successesLogin by remember { viewModel.succses }
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }

    if (successesLogin) {
        navController.navigate("login_screen") {
            popUpTo("register_screen") {
                inclusive = true
            }
        }
    }
    ErrorControllerErrorOnlyTextComponent(loading, error) {
        MyContent(
            Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            requestUser, viewModel
        )
    }
}

@Composable
fun MyContent(modifier: Modifier, requestUser: ResponseUser, viewModel: RegisterViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 16.dp, end = 16.dp),
        ) {
            item {
                Text(
                    text = "Hesab bilgilerini giriniz",
                    color = colorResource(R.color.text_color),
                    modifier = Modifier
                        .padding(top = 32.dp, bottom = 32.dp)
                        .fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "Adınız",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Adınız",
                    modifier = modifier
                ) {
                    requestUser.name = it
                }
            }

            item {
                Text(
                    text = "Soyadınız",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Soyadınız",
                    modifier = modifier
                ) {
                    requestUser.lastName = it
                }
            }

            item {
                Text(
                    text = "Kullanıcı adınız",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Kullanıcı adınız",
                    modifier = modifier
                ) {
                    requestUser.username = it
                }
            }

            item {
                Text(
                    text = "Email",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Email",
                    modifier = modifier
                ) {
                    requestUser.userEmail = it
                }
            }

            item {
                Text(
                    text = "Şifre",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextPassword(
                    text = "Şifre",
                    modifier = modifier
                ) {
                    requestUser.userPassword = it
                }
            }

            item {
                Text(
                    text = "Telefon Numarası",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Telefon Numarası",
                    modifier = modifier
                ) {
                    requestUser.phone = it
                }
            }

            item {
                Text(
                    text = "TC Kimlik Numarası",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "TC Kimlik Numarası",
                    modifier = modifier
                ) {
                    requestUser.tc = it
                }
            }
            item {
                BasicButton("Giriş Yap") {
                    if (requestUser.isNullData()) {
                        viewModel.saveUser()
                    } else {
                        openDialog.value = true
                    }
                }
            }
        }
    }
    if (openDialog.value) {
        // openDialog, openDialog, editMessage
        CustomDialog(openDialog = openDialog, text = "Tüm Bilgileri Doldurun", Image = R.drawable.ic_baseline_error_24) {
        }
    }
}
