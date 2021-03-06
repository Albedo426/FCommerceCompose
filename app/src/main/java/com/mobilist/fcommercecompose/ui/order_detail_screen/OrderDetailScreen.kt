package com.mobilist.fcommercecompose.ui.order_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.ui.categories_screen.lower_simple_category.MainContentLowerSimpleCategoryScreen
import com.mobilist.fcommercecompose.ui.components.button.MyExtendedFloatingActionButton
import com.mobilist.fcommercecompose.ui.components.cards.BasicCard
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.dropdown_list.DropDown
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.text.Title
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.order_detail_screen.component.OrderDetailOutlinedTextField

@Composable
fun OrderDetailScreen(
    navController: NavController,
    viewModel: OrderDetailViewModel = hiltViewModel()
) {

    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    ErrorControllerErrorOnlyTextComponent(loading, error) {
        OrderDetailContents(navController, viewModel)
    }
}

@Composable
fun OrderDetailContents(
    navController: NavController,
    viewModel: OrderDetailViewModel
) {
    val allPrice by remember { viewModel.allPrice }
    var shipAddressId by remember { viewModel.shipAddressId }
    var billAddressId by remember { viewModel.billAddressId }
    val openDialog = remember { mutableStateOf(false) }
    val modifier = Modifier
        .padding(10.dp)
    Column() {
        BasicTopBar("Sipari?? Detay??")
        LazyColumn(Modifier.weight(0.1f)) {
            item() {
                BasicCard(padding = 10) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Title("Teslim Adresi", "Ekle/D??zenle") {
                            navController.navigate("address_change_screen")
                        }
                        MySpacerHorizontal()
                        DropDown("Fatura Adresi", viewModel.listAddress) {
                            shipAddressId = it
                        }
                        DropDown("Teslim Adresi", viewModel.listAddress) {
                            billAddressId = it
                        }
                    }
                }
            }
            item() {
                BasicCard(padding = 10) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Title("Kart Bilgileri")
                        MySpacerHorizontal()
                        CardDetail()
                    }
                }
            }
            item() {
                BasicCard(padding = 10) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Title("S??zle??meler")
                    }
                }
            }
        }

        val modifierBottom = Modifier
            .padding(0.dp)
            .background(color = Color.White)
            .padding(5.dp)
            .fillMaxWidth()
        Row(
            modifierBottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(text = "Toplam", color = colorResource(id = R.color.text_colorDark))
                Text(text = "%.2f".format(allPrice), color = colorResource(id = R.color.text_colorDark))
            }
            MyExtendedFloatingActionButton(text = "Onayla ve Bitir", Icons.Filled.Add) {
                openDialog.value = true
                viewModel.applyOrder()
            }
        }
    }
    if (openDialog.value) {
        // openDialog, openDialog, editMessage
        CustomDialog(
            openDialog = openDialog,
            text = "Sipari??iniz Al??nm????t??r",
            acceptText = "Tamam",
            Image = R.drawable.ic_baseline_success_24
        ) {
            navController.navigate("main_screen")
        }
    }
}

@Composable
fun CardDetail() {
    var cardNo = remember { mutableStateOf("") }
    var cardYear = remember { mutableStateOf("") }
    var cardMount = remember { mutableStateOf("") }
    var cardCCV = remember { mutableStateOf("") }

    Column() {
        OrderDetailOutlinedTextField(
            title = "Kart Numaras??", str = cardNo,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Son Kullanma Tarihi", fontWeight = FontWeight.W700, color = colorResource(id = R.color.text_colorDark))
            Text("CVV", fontWeight = FontWeight.W700, modifier = Modifier.padding(end = 5.dp), color = colorResource(id = R.color.text_colorDark))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                OrderDetailOutlinedTextField(
                    title = "Ay", str = cardMount,
                    Modifier
                        .width(90.dp)
                        .padding(5.dp)
                )
                OrderDetailOutlinedTextField(
                    title = "Y??l", str = cardYear,
                    Modifier
                        .width(90.dp)
                        .padding(5.dp)
                )
            }
            Row() {
                OrderDetailOutlinedTextField(
                    title = "", str = cardCCV,
                    Modifier
                        .width(90.dp)
                        .padding(5.dp)
                )
            }
        }
    }
}
