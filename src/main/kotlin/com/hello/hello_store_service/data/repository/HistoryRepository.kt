package com.hello.hello_store_service.data.repository

import com.hello.hello_store_service.data.entity.HistoryEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface HistoryRepository : MongoRepository<HistoryEntity, String> {
    fun findByUid(uid: String): HistoryEntity?
}