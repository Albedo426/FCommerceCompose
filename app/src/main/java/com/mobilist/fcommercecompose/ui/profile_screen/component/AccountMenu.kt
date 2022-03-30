package com.mobilist.fcommercecompose.ui.profile_screen.component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.main_screen.bottom_navigate_bar.NavigationItem
import com.mobilist.fcommercecompose.ui.profile_screen.ProfileViewModel

@Composable
fun AccountMenu(navController: NavHostController, navControllerMain: NavController, viewModel: ProfileViewModel) {

    AccountMenuItem("Benim Siparişlerim", R.drawable.ic_baseline_shopping_cart_24) {
        navController.navigate("my_order_screen")
    }
    AccountMenuItem("Gelen Siparişler", R.drawable.ic_baseline_shopping_basket_24) {
        navController.navigate("in_coming_order_screen")
    }
    AccountMenuItem("Değerlendirmeler", R.drawable.ic_baseline_comment_24) {

    }
    AccountMenuItem("Ürün Ekle", R.drawable.ic_baseline_add_box_24) {

    }
    AccountMenuItem("Mesajlar", R.drawable.ic_baseline_chat_24) {

    }
    AccountMenuItem("Kullanıcı Bilgileri", R.drawable.ic_baseline_account_circle_24) {
        navController.navigate("user_change_screen")
    }
    AccountMenuItem("Adress Bilgileri", R.drawable.ic_baseline_location_on_24) {
        navController.navigate("address_change_screen")
    }
    AccountMenuItem("E-Posta Değişikliği", R.drawable.ic_baseline_mail_outline_24) {
        navController.navigate("mail_change_screen")

    }
    AccountMenuItem("Şifre Değişikliği", R.drawable.ic_baseline_lock_24) {
        navController.navigate("password_change_screen")
    }
    AccountMenuItem("Çıkış", R.drawable.ic_baseline_power_settings_new_24) {
        viewModel.exitClick(navControllerMain)
    }
}



@Composable
fun  SimpleAlertDialog(myOpenDialog:Boolean,onClick: (Boolean) -> Unit) {
    val openDialog = remember { mutableStateOf(myOpenDialog)  }
    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(text = "Çıkış")
        },
        text = {
            Text("Çıkış Yapmak istediğinizden emin misiniz")
        },
        confirmButton = {
            Button(
                onClick = {
                    openDialog.value = false
                }) {
                Text("Evet")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    openDialog.value = false
                }) {
                Text("hayır")
            }
        }
    )
}
