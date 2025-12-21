package com.hello.hello_store_service.data.repository

import com.hello.hello_store_service.data.entity.CommentEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentsRepository : MongoRepository<CommentEntity, String> {
    fun findBySpuId(spuId: String): List<CommentEntity>
}