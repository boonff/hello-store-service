package com.hello.hello_store_service.repository

import com.hello.hello_store_service.model.entity.UserCredential
import org.springframework.data.mongodb.repository.MongoRepository

interface UserCredentialRepository : MongoRepository<UserCredential, String> {
    fun findByUserId(userId: String): UserCredential?
}