package com.hello.hello_store_service.data.repository

import com.hello.hello_store_service.data.entity.order.OrderEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : MongoRepository<OrderEntity, String> {
    fun findByUid(uid: String): List<OrderEntity>
}