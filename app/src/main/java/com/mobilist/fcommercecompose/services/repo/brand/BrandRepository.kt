package com.mobilist.fcommercecompose.services.repo.brand

import com.mobilist.fcommercecompose.data.entity.product.Brand
import com.mobilist.fcommercecompose.util.Resource

interface BrandRepository {
    suspend fun getBrandList(): Resource<List<Brand>>
}
