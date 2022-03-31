package com.mobilist.fcommercecompose.ui.profile_screen.user_change_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.text.BasicOutlinedText
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.profile_screen.my_order_screen.my_order_detail.MainContentMyOrderDetailScreen

@Composable
fun UserChangeScreen(navController: NavHostController, viewModel: UserChangeViewModel = hiltViewModel()) {
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    ErrorControllerErrorOnlyTextComponent(loading, error) {
        MainContentUserChangeScreen(navController, viewModel)
    }
}

@Composable
fun MainContentUserChangeScreen(navController: NavHostController, viewModel: UserChangeViewModel) {
    val openDialog = remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        BasicTopBar("Kullanıcı Ayarları")
        LazyColumn(Modifier.weight(0.1f)) {
            item {
                UserChangeAttr(viewModel, openDialog)
            }
        }
    }
    if (openDialog.value) {
        // openDialog, openDialog, editMessage
        CustomDialog(openDialog = openDialog, text = "Güncelleme İşlemi Başarılı", acceptText = "Tamam", Image = R.drawable.ic_baseline_success_24) {
            navController.navigate("profile_screen")
        }
    }
}
@Composable
fun UserChangeAttr(viewModel: UserChangeViewModel, openDialog: MutableState<Boolean>) {
    val user by remember { viewModel.myUser }
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
                BasicOutlinedText(user.username!! + user.lastName, text = "Ad Soyad") {
                    user.username = it
                }
                BasicOutlinedText(user.phone!!, text = "Telefon Numarası") {
                    user.phone = it
                }
                BasicButton(text = "Güncelle") {
                    viewModel.changeUserNameAndMail(openDialog)
                }
            }
        }
    }
}
