package com.hello.hello_store_service.data.repository

import com.hello.hello_store_service.data.entity.CartEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : MongoRepository<CartEntity, String> {
    fun findByUid(uid: String): CartEntity?
    fun deleteByUid(uid: String): CartEntity?
}