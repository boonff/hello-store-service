package com.hello.hello_store_service.data.repository.user

import com.hello.hello_store_service.data.model.entity.user.UserCredentialEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserCredentialRepository : MongoRepository<UserCredentialEntity, String> {
    fun findByUsername(userId: String): UserCredentialEntity?
}