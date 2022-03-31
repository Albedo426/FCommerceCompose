package com.mobilist.fcommercecompose.services.repo.product

import com.mobilist.fcommercecompose.data.entity.informative.Comment
import com.mobilist.fcommercecompose.data.entity.informative.Like
import com.mobilist.fcommercecompose.data.entity.informative.Score
import com.mobilist.fcommercecompose.data.entity.product.Product
import com.mobilist.fcommercecompose.data.entity.product.ProductImage
import com.mobilist.fcommercecompose.data.entity.product.ProductMainItem
import com.mobilist.fcommercecompose.data.model.* // ktlint-disable no-wildcard-imports
import com.mobilist.fcommercecompose.services.room.product_api.ProductDao
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.util.getNowTimeString
import java.lang.Exception
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao) :
    ProductRepository {

    override suspend fun insert(product: Product): Long {
        return productDao.insert(product)
    }
    override suspend fun insertAll(vararg users: Product): List<Long> {
        return productDao.insertAllProduct(*users)
    }
    // maplama yap ad değiştir get main product tarzı olsun prodcutmainitemmodele dönüşsün veriler map ile
    override suspend fun getAllProduct(): Resource<List<Product>> {
        return try {
            val resource = productDao.getAllProduct()
            if (resource.isEmpty()) {
                Resource.Error("Ürün Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getCommentableProduct(Id: Int, str: String): Resource< List<CommentProductModel>> {
        return try {
            val resource = productDao.getCommentableProduct(Id, str)
            if (resource.isEmpty()) {
                Resource.Error("Henüz bir ürün Teslim Almadınız")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun getCommentableProduct(Id: Int, productId: Int): Resource< CommentProductModel> {
        return try {
            val resource = productDao.getCommentableProduct(Id, productId)
            Resource.Success(resource)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun addComment(Id: Int, productId: Int, str: String, point: Int): Resource< Boolean> {
        return try {
            val resourceScore = productDao.insert(Score(Id, productId, point))
            val resourceComment = productDao.insert(Comment("", str, "".getNowTimeString(), Id, productId))
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }


    override suspend fun getImageByProductId(Id: Int): Resource<List<ProductImage>> {
        return try {
            val resource = productDao.getImageByProductId(Id)
            println(resource.size)
            if (resource.isEmpty()) {
                Resource.Error("Ürün Resmi")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun getProductDetailById(Id: Int, userId: Int): Resource<DetailProductModel> {
        return try {
            val resource = productDao.getProductDetailById(Id, userId)
            if (resource == null) {
                Resource.Error("Ürün Detayı Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun getProductById(Id: Int): Resource<Product> {
        return try {
            Resource.Success(productDao.getProductById(Id))
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getProductLikeCountById(Id: Int): Resource<Int> {
        return try {
            Resource.Success(productDao.getProductLikeCountById(Id))
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getProductCommentLastById(Id: Int): Resource<List<CommentDetailModel>> {
        return try {
            val resource = productDao.getProductCommentLastById(Id)
            println(resource.size)
            if (resource.isEmpty()) {
                Resource.Error("Yorum Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getProductScoreCountById(Id: Int): Resource<Double> {
        return try {
            val resource = productDao.getProductScoreCountById(Id)
            if (resource == null) {
                Resource.Error("Score Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun allProductByCategoryId(Id: Int): List<Product> {
        return productDao.allProductByCategoryId(Id)
    }

    override suspend fun allProductByName(name: String): List<Product> {
        return productDao.allProductByName(name)
    }

    override suspend fun getHomeProduct(Id: Int): Resource<List<ProductMainItem>> {
        return try {
            val resource = productDao.getHomeProduct(Id)
            if (resource.isEmpty()) {
                Resource.Error("Ürün Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getSearchProduct(Id: Int, str: String): Resource<List<ProductMainItem>> {
        return try {
            val resource = productDao.getSearchProduct(Id, str)
            if (resource.isEmpty()) {
                Resource.Error("Ürün Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    // ürün Id
    override suspend fun isLike(ProductId: Int, UserId: Int): Resource<Boolean> {
        return try {
            val resource = productDao.isLike(ProductId, UserId)
            if (resource.isEmpty()) {
                val resourceAdd = productDao.addLike(Like(product = ProductId, user = UserId))
                Resource.Success(true)
            } else {
                val resourceRemove = productDao.removeLike(ProductId, UserId)
                Resource.Success(true)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getFavoriteProduct(Id: Int): Resource<List<ProductMainItem>> {
        return try {
            val resource = productDao.getFavoriteProduct(Id)
            if (resource.isEmpty()) {
                Resource.Error("henüz Bir Ürünü Beğenmediniz")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun getSearchFavoriteProduct(Id: Int, str: String): Resource<List<ProductMainItem>> {
        return try {
            val resource = productDao.getSearchFavoriteProduct(Id, str)
            if (resource.isEmpty()) {
                Resource.Error("Beğenilenlerde Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getCategoryProduct(Id: Int): Resource<List<ProductMainItem>> {
        return try {
            val resource = productDao.getCategoryProduct(Id)
            if (resource.isEmpty()) {
                Resource.Error("Ürün Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
    override suspend fun getSearchCategoryProduct(Id: Int, str: String): Resource<List<ProductMainItem>> {
        return try {
            val resource = productDao.getSearchCategoryProduct(Id, str)
            if (resource.isEmpty()) {
                Resource.Error("Ürün Bulunamadı")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
}
