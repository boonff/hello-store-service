package com.hello.hello_store_service.repository

import com.hello.hello_store_service.model.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?
}