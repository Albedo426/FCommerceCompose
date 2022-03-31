package com.mobilist.fcommercecompose.services.repo.order

import com.mobilist.fcommercecompose.data.entity.shopping_list.Order
import com.mobilist.fcommercecompose.data.model.MyOrderStatusResponseModel
import com.mobilist.fcommercecompose.data.model.RequestOrderModel
import com.mobilist.fcommercecompose.services.room.order_api.OrderDao
import com.mobilist.fcommercecompose.util.Resource
import java.lang.Exception
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(private val orderDao: OrderDao) : OrderRepository {

    override suspend fun insert(order: Order): Resource<Boolean> {
        return try {
            orderDao.insert(order)
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getMyOrder(Id: Int): Resource< List<RequestOrderModel>> {
        return try {
            val resource = orderDao.getMyOrder(Id)
            if (resource.isEmpty()) {
                Resource.Error("Sepette ürününüz yok")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getMyOrderAll(Id: Int): Resource< List<RequestOrderModel>> {
        return try {
            val resource = orderDao.getMyOrderAll(Id)
            if (resource.isEmpty()) {
                Resource.Error("Sepette ürününüz yok")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getInComingOrderAll(Id: Int): Resource< List<RequestOrderModel>> {
        return try {
            val resource = orderDao.getInComingOrderAll(Id)
            if (resource.isEmpty()) {
                Resource.Error("Gelen Siparişiniz Yok")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getOrderStatusAll(Id: Int): Resource<MyOrderStatusResponseModel> {
        return try {
            val resource = orderDao.getOrderStatusAll(Id)
            if (resource.UUID == 0) {
                Resource.Error("Siparişlerde Bir Sıkıntı Oluştu")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun updateOrderStatus(type: Int, billAddress: Int, shipAddress: Int, Id: IntArray): Resource<Boolean> {
        return try {
            if (billAddress != 0 && shipAddress != 0) {
                orderDao.updateOrderStatus(type, billAddress, shipAddress, Id)
                Resource.Success(true)
            } else {
                throw Exception("Fatura Veya Sipariş Adresi Girilmedi ")
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun updateOrderStatus(type: Int, cargoNo: String, trackingNo: String, Id: Int): Resource<Boolean> {
        return try {
            orderDao.updateOrderStatus(type, cargoNo, trackingNo, Id)
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun removeMyOrder(Id: Int): Resource<Boolean> {
        return try {
            orderDao.removeMyOrder(Id)
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun increaseMyOrder(Id: Int): Resource<Boolean> {
        return try {
            orderDao.increaseMyOrder(Id)

            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun reduceMyOrder(Id: Int): Resource<Boolean> {
        return try {
            orderDao.reduceMyOrder(Id)

            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
}
