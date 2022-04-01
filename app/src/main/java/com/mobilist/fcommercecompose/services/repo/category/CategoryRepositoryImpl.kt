package com.mobilist.fcommercecompose.services.repo.category

import com.mobilist.fcommercecompose.data.entity.product.Category
import com.mobilist.fcommercecompose.services.room.category_api.CategoryDao
import com.mobilist.fcommercecompose.util.Resource
import java.lang.Exception
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao) :
    CategoryRepository {
    override suspend fun getCategoriesMainProduct(): Resource<List<Category>> {
        return try {
            listControl(categoryDao.getCategoriesMainProduct())
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun searchCategoriesMainProduct(str: String): Resource<List<Category>> {
        return try {
            listControl(categoryDao.searchCategoriesMainProduct(str))
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getCategoriesLowerMainProduct(Id: Int): Resource<List<Category>> {
        return try {
            listControl(categoryDao.getCategoriesLowerMainProduct(Id))
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun searchCategoriesLowerMainProduct(Id: Int, str: String): Resource<List<Category>> {
        return try {
            listControl(categoryDao.searchCategoriesLowerMainProduct(Id, str))
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getCategoriesLowerSimpleProduct(Id: Int): Resource<List<Category>> {
        return try {
            listControl(categoryDao.getCategoriesLowerSimpleProduct(Id))
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun getCategoriesLowerProduct(): Resource<List<Category>> {
        return try {
            listControl(categoryDao.getCategoriesLowerProduct())
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun searchCategoriesLowerSimpleProduct(Id: Int, str: String): Resource<List<Category>> {
        return try {
            listControl(categoryDao.searchCategoriesLowerSimpleProduct(Id, str))
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    private fun listControl(resource: List<Category>): Resource<List<Category>> {
        return if (resource.isEmpty()) {
            Resource.Error("Kategori Yok")
        } else {
            Resource.Success(resource)
        }
    }
}
