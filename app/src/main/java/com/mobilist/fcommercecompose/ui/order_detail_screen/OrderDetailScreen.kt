package com.mobilist.fcommercecompose.ui.order_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size.Companion.Zero
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.model.UserAddressModel
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.dropdown_list.DropDown
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.text.Title
import com.mobilist.fcommercecompose.ui.components.top_bar.BasicTopBar
import com.mobilist.fcommercecompose.ui.register_screen.MyContent

@Composable
fun OrderDetailScreen(
    navController: NavController,
    viewModel: OrderDetailViewModel = hiltViewModel()
) {

    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    if (error != "" || loading) {
        ErrorOnlyTextComponent(loading, error)
    } else {
        OrderDetailContents(navController, viewModel)
    }
}

@Composable
fun OrderDetailContents(
    navController: NavController,
    viewModel: OrderDetailViewModel = hiltViewModel()
) {
    val allPrice by remember { viewModel.allPrice }
    var shipAddressId by remember { viewModel.shipAddressId }
    var billAddressId by remember { viewModel.billAddressId }
    val openDialog = remember { mutableStateOf(false) }
    val modifier = Modifier
        .padding(10.dp)
    Column(Modifier) {
        BasicTopBar("Sipariş Detayı")
        LazyColumn(Modifier.weight(0.1f)) {
            item() {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
                    Card(
                        elevation = 4.dp,
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Title("Teslim Adresi", "Ekle/Düzenle") {
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
            }
            item() {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
                    Card(
                        elevation = 4.dp,
                    ) {
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
            }
            item() {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
                    Card(
                        elevation = 4.dp,
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
                            Title("Sözleşmeler")
                        }
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
                Text(text = "Toplam",color=colorResource(id = R.color.text_colorDark))
                Text(text = "%.2f".format(allPrice),color=colorResource(id = R.color.text_colorDark))
            }
            ExtendedFloatingActionButton(
                text = { Text(text = "Onayla ve Bitir", color = Color.White) },
                icon = { Icon(Icons.Filled.Add, "", tint = Color.White) },
                backgroundColor = colorResource(R.color.mainBlue),
                onClick = {
                    openDialog.value = true
                    viewModel.applyOrder()
                })
        }
    }
    if (openDialog.value) {
        // openDialog, openDialog, editMessage
        CustomDialog(
            openDialog = openDialog,
            text = "Siparişiniz Alınmıştır",
            acceptText = "Tamam",
            Image = R.drawable.ic_baseline_success_24
        ) {
            navController.navigate("main_screen")
        }
    }
}

@Composable
fun CardDetail() {
    var cardNo by remember { mutableStateOf("") }
    var cardYear by remember { mutableStateOf("") }
    var cardMount by remember { mutableStateOf("") }
    var cardCCV by remember { mutableStateOf("") }

    Column() {
        OutlinedTextField(
            value = cardNo,
            onValueChange = { cardNo = it },
            modifier = Modifier
                .fillMaxWidth(),
            label = { Text("Kart Numarası") }, colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.text_colorDark),
                unfocusedBorderColor = colorResource(id = R.color.text_colorDark),
                focusedLabelColor = colorResource(id = R.color.text_colorDark),
                cursorColor = colorResource(id = R.color.text_colorDark),
                textColor = colorResource(id = R.color.text_colorDark)
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Son Kullanma Tarihi", fontWeight = FontWeight.W700,color=colorResource(id = R.color.text_colorDark))
            Text("CVV", fontWeight = FontWeight.W700, modifier = Modifier.padding(end = 5.dp),color=colorResource(id = R.color.text_colorDark))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                OutlinedTextField(
                    singleLine = true,
                    value = cardMount,
                    onValueChange = { cardMount = it },
                    modifier = Modifier
                        .width(90.dp)
                        .padding(5.dp),
                    label = { Text("Ay") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.text_colorDark),
                        unfocusedBorderColor = colorResource(id = R.color.text_colorDark),
                        focusedLabelColor = colorResource(id = R.color.text_colorDark),
                        cursorColor = colorResource(id = R.color.text_colorDark),
                        textColor = colorResource(id = R.color.text_colorDark)
                    )
                )
                OutlinedTextField(
                    singleLine = true,
                    value = cardYear,
                    onValueChange = { cardYear = it },
                    modifier = Modifier
                        .width(90.dp)
                        .padding(5.dp),
                    label = { Text("Yıl") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.text_colorDark),
                        unfocusedBorderColor = colorResource(id = R.color.text_colorDark),
                        focusedLabelColor = colorResource(id = R.color.text_colorDark),
                        cursorColor = colorResource(id = R.color.text_colorDark),
                        textColor = colorResource(id = R.color.text_colorDark)
                    )
                )
            }
            Row() {
                OutlinedTextField(
                    singleLine = true,
                    value = cardCCV,
                    onValueChange = { cardCCV = it },
                    modifier = Modifier
                        .width(90.dp)
                        .padding(5.dp),
                    label = { Text("") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.text_colorDark),
                        unfocusedBorderColor = colorResource(id = R.color.text_colorDark),
                        focusedLabelColor = colorResource(id = R.color.text_colorDark),
                        cursorColor = colorResource(id = R.color.text_colorDark),
                        textColor = colorResource(id = R.color.text_colorDark)
                    )
                )
            }
        }
    }
}
