package com.hello.hello_store_service.data.repository

import com.hello.hello_store_service.data.entity.address.LogisticEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LogisticRepository : MongoRepository<LogisticEntity, String> {
    fun findByOrderId(orderId: String): LogisticEntity?
}