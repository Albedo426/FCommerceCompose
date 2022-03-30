package com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.address_add_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.dropdown_list.DropDownOnlyString
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.text.BasicOutlinedText
import com.mobilist.fcommercecompose.ui.components.text.Title
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component.MainContentAddressView
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component.TopBar


@Composable
fun AddressAddScreen(navController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        BasicTopBar("Adress Değişikliği")
        MainContentMailChangeScreen(navController)
    }
}

@Composable
fun MainContentMailChangeScreen(navController: NavHostController) {
    val openDialog = remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(0.1f)) {
            item() {
                AddedAddressComp(openDialog)
            }
        }
    }
    if (openDialog.value) {
        // openDialog, openDialog, editMessage
        CustomDialog(openDialog= openDialog ,text="Ekleme İşlemi Başarılı", acceptText = "Tamam", Image = R.drawable.ic_baseline_success_24 ){
            navController.navigate("address_change_screen"){
                popUpTo("address_add_screen") {
                    inclusive = true
                }
                popUpTo("address_change_screen") {
                    inclusive = true
                }
            }
        }
    }
}
@Composable
fun AddedAddressComp(openDialog:MutableState<Boolean>,viewModel: AddressAddViewModel= hiltViewModel()) {
    val city by remember{viewModel.cityMaxList}
    val address by remember{viewModel.createdAddress}
    var selectedCity by remember{ mutableStateOf("")}
    var selectedState by remember{ mutableStateOf("")}

    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Card(
            elevation = 4.dp,
        ) {
            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {
                Title("Adress Bilgileri")
                MySpacerHorizontal()
                BasicOutlinedText("",text="Adres Başlığı"){address.addressTitle=it}
                BasicOutlinedText("",text="Telefon Numarası"){address.phone=it}
                BasicOutlinedText("",text="PostaKodu"){address.postalCode=it.toInt()}
                BasicOutlinedText("",text="Açık Adress",line=5){address.address=it}
            }
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Card(
            elevation = 4.dp,
        ) {
            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {
                Title("Adress Bilgileri")
                MySpacerHorizontal()
                DropDownOnlyString("İller",listAddress =  city.map { it.city }){
                    selectedCity=it
                    address.city=it
                }
                BasicButton(text ="Kaydet"){
                    viewModel.addAddress(address,openDialog)
                }
            }
        }
    }
}