package com.mobilist.fcommercecompose.services.repo.brand

import com.mobilist.fcommercecompose.data.entity.product.Brand
import com.mobilist.fcommercecompose.services.room.brand_api.BrandDao
import com.mobilist.fcommercecompose.util.Resource
import java.lang.Exception
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(private val brandDao: BrandDao) :
    BrandRepository {
    override suspend fun getBrandList(): Resource<List<Brand>> {
        return try {
            val resource = brandDao.getBrandList()
            return if (resource.isEmpty()) {
                Resource.Error("Kategori Yok")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
}
