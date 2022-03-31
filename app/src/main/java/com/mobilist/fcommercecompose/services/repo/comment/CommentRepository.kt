package com.mobilist.fcommercecompose.services.repo.comment

import com.mobilist.fcommercecompose.data.model.CommentDetailModel
import com.mobilist.fcommercecompose.data.model.CommentProductModel
import com.mobilist.fcommercecompose.util.Resource

interface CommentRepository {
    suspend fun getCommentableProduct(Id: Int, str: String): Resource<List<CommentProductModel>>
    suspend fun getCommentableProduct(Id: Int, productId: Int): Resource< CommentProductModel>
    suspend fun addComment(Id: Int, productId: Int, str: String, point: Int): Resource< Boolean>
    suspend fun getProductCommentLastById(Id: Int): Resource<List<CommentDetailModel>>
}