package com.hello.hello_store_service.data.repository.comment

import com.hello.hello_store_service.data.model.entity.CommentEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentsRepository : MongoRepository<CommentEntity, String>, CommentsRepositoryCustom {
    fun findBySpuId(spuId: String): List<CommentEntity>
}

interface CommentsRepositoryCustom {
    fun findByRange(spuId: String, pageIndex: Int, pageSize: Int): List<CommentEntity>
    fun findDetail(spuId: String, pageIndex: Int, pageSize: Int, hasImage: Boolean, commentLevel: Int): List<CommentEntity>
    fun findRandomTop(spuId: String, randomSize: Int, selectSize: Int): List<CommentEntity>
}