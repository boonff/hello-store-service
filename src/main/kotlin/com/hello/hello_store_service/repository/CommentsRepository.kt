package com.hello.hello_store_service.repository

import com.hello.hello_store_service.model.entity.Comments
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentsRepository : MongoRepository<Comments, String>, CommentsRepositoryCustom {
    fun findBySpuId(spuId: String): List<Comments>
}

interface CommentsRepositoryCustom {
    fun findByRange(spuId: String, pageIndex: Int, pageSize: Int): List<Comments>
    fun findDetail(spuId: String, pageIndex: Int, pageSize: Int, hasImage: Boolean, commentLevel: Int): List<Comments>
    fun findRandomTop(spuId: String, randomSize: Int, selectSize: Int): List<Comments>
}