package com.hello.hello_store_service.repository.user

import com.hello.hello_store_service.model.entity.user.UserCredentialEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserCredentialRepository : MongoRepository<UserCredentialEntity, String> {
    fun findByUsername(userId: String): UserCredentialEntity?
}