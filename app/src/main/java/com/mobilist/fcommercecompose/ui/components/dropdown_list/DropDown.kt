package com.mobilist.fcommercecompose.ui.components.dropdown_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.model.UserAddressModel


@Composable
fun DropDown(text:String, listAddress: MutableState<List<UserAddressModel>>, onRetry: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Adres Seçin") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    Column(modifier=Modifier.padding(top=5.dp)) {
        OutlinedTextField(
            value = selectedText,
            enabled = false,
            onValueChange = {
                selectedText= it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(text ,color=colorResource(id = R.color.text_colorDark)) },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor= colorResource(id = R.color.text_colorDark),
                unfocusedBorderColor = colorResource(id = R.color.text_colorDark),
                focusedLabelColor = colorResource(id = R.color.text_colorDark),
                cursorColor = colorResource(id = R.color.text_colorDark),
                textColor = colorResource(id = R.color.text_colorDark)
            )

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            listAddress.value.forEach { label ->
                DropdownMenuItem(onClick = {
                    onRetry(label.UUID)
                    expanded = !expanded
                    selectedText = label.addressTitle
                }) {
                    Text(text = label.addressTitle )
                }
            }
        }
    }
}

@Composable
fun DropDownOnlyString(text:String,showString:String="Adres Seçin", listAddress: List<String>, onRetry: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(showString) }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    Column() {
        OutlinedTextField(
            value = selectedText,
            enabled = false,
            onValueChange = {
                selectedText= it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(text) },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            listAddress.forEach { label ->
                DropdownMenuItem(onClick = {
                    onRetry(label)
                    expanded = !expanded
                    selectedText = label
                }) {
                    Text(text = label)
                }
            }
        }
    }
}
