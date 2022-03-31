package com.mobilist.fcommercecompose.services.repo.comment

import com.mobilist.fcommercecompose.data.entity.informative.Comment
import com.mobilist.fcommercecompose.data.entity.informative.Score
import com.mobilist.fcommercecompose.data.model.CommentDetailModel
import com.mobilist.fcommercecompose.data.model.CommentProductModel
import com.mobilist.fcommercecompose.services.room.comment_api.CommentDao
import com.mobilist.fcommercecompose.util.Resource
import com.mobilist.fcommercecompose.util.getNowTimeString
import java.lang.Exception
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(private val commentDao: CommentDao) :
    CommentRepository {
    override suspend fun getCommentableProduct(
        Id: Int,
        str: String
    ): Resource<List<CommentProductModel>> {
        return try {
            val resource = commentDao.getCommentableProduct(Id, str)
            if (resource.isEmpty()) {
                Resource.Error("Henüz bir ürün Teslim Almadınız")
            } else {
                Resource.Success(resource)
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getCommentableProduct(
        Id: Int,
        productId: Int
    ): Resource<CommentProductModel> {
        return try {
            val resource = commentDao.getCommentableProduct(Id, productId)
            Resource.Success(resource)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun addComment(
        Id: Int,
        productId: Int,
        str: String,
        point: Int
    ): Resource<Boolean> {
        return try {
            commentDao.insert(Score(Id, productId, point))
            commentDao.insert(Comment("", str, "".getNowTimeString(), Id, productId))
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }

    override suspend fun getProductCommentLastById(Id: Int): Resource<List<CommentDetailModel>> {
        return try {
            val resource = commentDao.getProductCommentLastById(Id)
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
}
