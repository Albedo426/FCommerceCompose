package com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.model.UserAddressModel
import com.mobilist.fcommercecompose.data.model.getFullName
import com.mobilist.fcommercecompose.ui.profile_screen.address_change_screen.AddressChangeViewModel


@Composable
fun AddressItem(address: UserAddressModel, openDialog: MutableState<Boolean>,addressChangeViewModel: AddressChangeViewModel= hiltViewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Card(modifier = Modifier
            .fillMaxWidth(),
            elevation = 4.dp,
        ) {
            Column(modifier= Modifier.padding(top = 10.dp)){
                Row() {
                    Icon(
                        painterResource(R.drawable.ic_baseline_location_on_24), "",
                        modifier = Modifier
                            .padding(15.dp))
                    Column(modifier = Modifier
                        .padding(top=15.dp,bottom = 10.dp,end = 5.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = address.addressTitle)
                            Image(
                                painterResource(R.drawable.ic_baseline_delete_outline_24),
                                contentDescription = "", modifier = Modifier
                                    .padding(3.dp)
                                    .clickable {
                                        addressChangeViewModel.remove(address)
                                    }
                            )
                        }

                        Text(text = address.getFullName(),modifier= Modifier
                            .padding(top = 10.dp)
                            .alpha(0.5f))
                        Text(text = address.phone,modifier= Modifier
                            .padding(top = 5.dp)
                            .alpha(0.5f))
                        Text(text = address.address,modifier = Modifier
                            .wrapContentWidth()
                            .padding(top = 5.dp)
                            .alpha(0.5f))
                    }
                }
            }
        }
    }
}
