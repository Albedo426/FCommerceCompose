package com.mobilist.fcommercecompose.ui.order_screen

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.mobilist.fcommerce.util.CustomSharedPreferences
import com.mobilist.fcommercecompose.base.BaseViewModel
import com.mobilist.fcommercecompose.data.model.*
import com.mobilist.fcommercecompose.services.repo.product.ProductRepositoryImpl
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.util.percentageDouble
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderScreenViewModel @Inject constructor(
    application: Application,
    private var productRepositoryImpl: ProductRepositoryImpl,
    private var customSharedPreferences: CustomSharedPreferences
) : BaseViewModel(application) {

    var list= mutableStateOf<List<RequestOrderModel>>(listOf())
    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)
    var allPrice= mutableStateOf(0.0)



    private suspend fun quantityController(plus:Boolean, price:Double, UUID:Int) :Resource<Boolean>{
        return if(plus){
            allPrice.value+=price
            productRepositoryImpl.increaseMyOrder(UUID)
        }else{
            allPrice.value-=price
            productRepositoryImpl.reduceMyOrder(UUID)
        }
    }
    fun changeAllPrice(plus:Boolean,price:Double,UUID:Int){
        launch{
            when(val result=quantityController(plus,price,UUID)){//değişecek deinamik olucak
                is Resource.Success ->{
                    errorMessage.value=""
                    isLoading.value=false
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                    isLoading.value=false
                }
            }
            isLoading.value=false
            loadShoppingList()
        }

    }
    fun remove(order:RequestOrderModel){
        launch{
            when(val result=productRepositoryImpl.removeMyOrder(order.UUID)){//değişecek deinamik olucak
                is Resource.Success->{
                    val items= result.data!!
                    allPrice.value=0.0
                    list.value= emptyList()
                    loadShoppingList()
                    errorMessage.value=""
                    isLoading.value=false
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                    isLoading.value=false
                }
            }
            isLoading.value=false
            loadShoppingList()
        }
        /*allPrice.value=0.0
        val iterator: MutableIterator<RequestOrderModel> = list.value.toMutableList().iterator()
        list.value= emptyList()
        while (iterator.hasNext()) {
            val str = iterator.next()
            if (str.UUID==order.UUID) {
                iterator.remove()
                continue
            }
            list.value+=str
            allPrice.value+=str.getProductTotalPrice()*str.Quantity
        }*/
    }

    fun loadShoppingList(){
        launch {
            when(val result=productRepositoryImpl.getMyOrder(customSharedPreferences.getUserId()!!)){
                is Resource.Success->{
                    val items= result.data!!
                    list.value=items
                    allPrice.value=0.0
                    items.forEach {
                        if(it.quantity==0){
                            it.quantity=1
                        }
                        allPrice.value+= it.productPrice.percentageDouble(it.productDiscountRate)*it.quantity
                    }
                    errorMessage.value=""
                    isLoading.value=false
                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                    isLoading.value=false
                }
            }
            isLoading.value=false
        }
    }
}