package com.mobilist.fcommercecompose.ui.order_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.mobilist.fcommercecompose.R
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerHorizontal
import com.mobilist.fcommercecompose.ui.components.line_component.MySpacerVertical
import com.mobilist.fcommercecompose.ui.order_screen.OrderScreenViewModel
import com.mobilist.fcommercecompose.util.percentageDouble

@Composable
fun OrderItem(order: RequestOrderModel, viewModel: OrderScreenViewModel = hiltViewModel()) {
    val isChecked = remember { mutableStateOf(true) }
    val itemQuantity = remember { mutableStateOf(order.quantity) }
    Card(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(10),
        backgroundColor = Color.White,
    ) {
        Column {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
              /*  Checkbox(modifier = Modifier.padding(5.dp),
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                    }
                )*/
                Text(text =order.name+" "+ order.lastName, modifier = Modifier.padding(5.dp))
            }
            MySpacerHorizontal()
            Row(Modifier.fillMaxHeight()) {
                Image(
                    rememberImagePainter(data = order.coverImagePath),
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .padding(start=5.dp),
                    contentDescription = "",
                )
                Column(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = order.productName, modifier = Modifier.padding(5.dp))
                        Image(
                            painterResource(R.drawable.ic_baseline_delete_outline_24),
                            contentDescription = "", modifier = Modifier.padding(3.dp).clickable{
                                viewModel.remove(order)
                            }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth() .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .clip(shape = CircleShape)
                                .height(IntrinsicSize.Min)
                                .background(colorResource(R.color.mainBlue))
                        ) {
                            Icon(
                                Icons.Filled.Add, "", tint = Color.White, modifier = Modifier
                                .padding(start = 3.dp, end = 3.dp)
                                .clickable {
                                    itemQuantity.value++
                                    viewModel.changeAllPrice(true,order.productPrice.percentageDouble(order.productDiscountRate),order.UUID)
                                    order.quantity=itemQuantity.value;
                                })

                            MySpacerVertical(
                                color = Color.White,

                                )
                            Text(
                                text = itemQuantity.value.toString(),
                                modifier = Modifier.padding(top=4.dp,start=7.dp,end = 7.dp,bottom = 4.dp),
                                color = Color.White
                            )
                            MySpacerVertical(
                                color = Color.White,

                                )
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_remove_24),
                                contentDescription = "print",
                                tint = Color.White,
                                modifier = Modifier.padding(end=3.dp,start = 3.dp) .clickable {
                                    if( itemQuantity.value>1){
                                        itemQuantity.value--
                                        viewModel.changeAllPrice(false,order.productPrice.percentageDouble(order.productDiscountRate),order.UUID)
                                        order.quantity=itemQuantity.value
                                    }
                                }
                            )
                        }

                        Text(
                            text =   "%.2f".format((order.productPrice.percentageDouble(order.productDiscountRate)*itemQuantity.value)) ,
                            modifier = Modifier.padding(5.dp)
                        )
                    }

                }
            }

        }
    }
}