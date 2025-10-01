package com.hello.hello_store_service.repository.user

import com.hello.hello_store_service.model.entity.user.UserCredential
import org.springframework.data.mongodb.repository.MongoRepository

interface UserCredentialRepository : MongoRepository<UserCredential, String> {
    fun findByUsername(userId: String): UserCredential?
}