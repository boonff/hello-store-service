package com.hello.hello_store_service.repository.user

import com.hello.hello_store_service.model.entity.user.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<UserEntity, String> {
    fun findByUsername(username: String): UserEntity?
}