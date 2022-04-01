package com.mobilist.fcommercecompose.ui.profile_screen.product_new

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.entity.product.toKeyValueModel
import com.mobilist.fcommercecompose.ui.components.button.BasicButton
import com.mobilist.fcommercecompose.ui.components.cards.BasicCard
import com.mobilist.fcommercecompose.ui.components.dialog.CustomDialog
import com.mobilist.fcommercecompose.ui.components.dropdown_list.DropDownOnlyKeyValue
import com.mobilist.fcommercecompose.ui.components.error_components.ErrorControllerErrorOnlyTextComponent
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.text.BasicOutlinedText
import com.mobilist.fcommercecompose.ui.components.text.Title
import com.mobilist.fcommercecompose.util.popUpToTop

@Composable
fun ProductNewAddScreen(
    navController: NavController,
    viewModel: ProductNewAddViewModel = hiltViewModel()
) {
    val error by remember { viewModel.errorMessage }
    val loading by remember { viewModel.isLoading }
    ErrorControllerErrorOnlyTextComponent(loading, error) {
        MainProductNewAdd(navController, viewModel)
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainProductNewAdd(
    navController: NavController,
    viewModel: ProductNewAddViewModel
) {
    val listCategory by remember { viewModel.listCategory }
    val product by remember { viewModel.product }
    val listBrand by remember { viewModel.listBrand }
    val brand by remember { viewModel.brand }
    val category by remember { viewModel.category }
    val openDialog = remember { mutableStateOf(false) }

    var imagePath1 by remember { viewModel.imagePath1 }
    var productPrice by remember { viewModel.productPrice }
    var imagePath2 by remember { viewModel.imagePath2 }
    var imagePath3 by remember { viewModel.imagePath3 }

    var declaration = remember { viewModel.declaration }
    var productName = remember { viewModel.productName }
    var productMinDeclaration = remember { viewModel.productMinDeclaration }
    Column(Modifier.padding(bottom = 60.dp)) {
        if (openDialog.value) {
            CustomDialog(openDialog = openDialog, text = "Ekleme İşlemi Başarılı", acceptText = "Tamam", Image = R.drawable.ic_baseline_success_24) {
                navController.navigate("profile_screen") {
                    popUpToTop(navController)
                }
            }
        }
        LazyColumn(Modifier.weight(0.1f)) {
            item() {
                BasicCard() {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Title("Ürün Bilgileri")
                        MySpacerHorizontal()
                        OutlinedTextField(
                            singleLine = true,
                            value = productName.value,
                            onValueChange = {productName.value = it },
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = { Text("Ürün Adı") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = colorResource(id = R.color.text_colorDark),
                                unfocusedBorderColor = colorResource(id = R.color.text_colorDark),
                                focusedLabelColor = colorResource(id = R.color.text_colorDark),
                                cursorColor = colorResource(id = R.color.text_colorDark),
                                textColor = colorResource(id = R.color.text_colorDark)
                            )
                        )
                        DropDownOnlyKeyValue(
                            "Kategori",
                            "Kategori Seçin",
                            listAddress = listCategory.map { it.toKeyValueModel() }
                        ) {
                            category.UUID = it
                            product.productCategory = it
                        }
                        DropDownOnlyKeyValue(
                            "Marka",
                            "Marka Seçin",
                            listAddress = listBrand.map { it.toKeyValueModel() }
                        ) {
                            brand.UUID = it
                            product.productBrand = it
                        }
                    }
                }
            }
            item() {
                BasicCard() {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Title("Ürün Özellikleri")

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 300.dp)
                                .padding(8.dp),
                            value = declaration.value,
                            onValueChange = { declaration.value = it },
                            label = { Text("Ürün Özellikleri") },
                            placeholder = { Text("Özellikleri girerken alt alta olacak şekilde girin (↩) ") }
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 150.dp)
                                .padding(8.dp),
                            value =productMinDeclaration.value,
                            onValueChange = {productMinDeclaration.value = it },
                            label = { Text("Ürün kısa tanım") }
                        )
                        BasicOutlinedText(
                            text = "Ürün Adeti"
                        ) {
                            product.quantity = it.toInt()
                        }
                        BasicOutlinedText(
                            text = "Ürün Resmi "
                        ) {
                            imagePath1 = it
                        }
                        BasicOutlinedText(
                            text = "Ürün Resmi 2"
                        ) {
                            imagePath2 = it
                        }
                        BasicOutlinedText(
                            text = "Ürün Resmi 3"
                        ) {
                            imagePath3 = it
                        }
                    }
                }
            }
            item() {
                BasicCard() {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Title("Ürün Fiyat Bilgileri")
                        BasicOutlinedText(
                            text = "İndirim oranı"
                        ) {
                            productPrice.productDiscountRate = it.toInt()
                        }
                        BasicOutlinedText(
                            text = "İndirim Başlangıç Tarhi"
                        ) {
                            productPrice.startDate = it
                        }
                        BasicOutlinedText(
                            text = "İndirim Biriş Tarhi"
                        ) {
                            productPrice.finishDate = it
                        }
                        BasicOutlinedText(
                            text = "Ürün Fiyatı"
                        ) {
                            productPrice.productPrice = it.toDouble()
                        }
                        BasicButton(
                            text = "Ürün ekle",
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                        ) {
                            viewModel.addProduct(openDialog)
                        }
                    }
                }
            }
        }
    }
}
