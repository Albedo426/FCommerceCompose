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
                    text = "Ad??n??z",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Ad??n??z",
                    modifier = modifier
                ) {
                    requestUser.name = it
                }
            }

            item {
                Text(
                    text = "Soyad??n??z",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Soyad??n??z",
                    modifier = modifier
                ) {
                    requestUser.lastName = it
                }
            }

            item {
                Text(
                    text = "Kullan??c?? ad??n??z",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Kullan??c?? ad??n??z",
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
                    text = "??ifre",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextPassword(
                    text = "??ifre",
                    modifier = modifier
                ) {
                    requestUser.userPassword = it
                }
            }

            item {
                Text(
                    text = "Telefon Numaras??",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "Telefon Numaras??",
                    modifier = modifier
                ) {
                    requestUser.phone = it
                }
            }

            item {
                Text(
                    text = "TC Kimlik Numaras??",
                    color = colorResource(R.color.text_color),
                    modifier = modifier
                )
            }
            item {
                TextName(
                    text = "TC Kimlik Numaras??",
                    modifier = modifier
                ) {
                    requestUser.tc = it
                }
            }
            item {
                BasicButton("Giri?? Yap") {
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
        CustomDialog(openDialog = openDialog, text = "T??m Bilgileri Doldurun", Image = R.drawable.ic_baseline_error_24) {
        }
    }
}
