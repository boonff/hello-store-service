package com.hello.hello_store_service.repository.user

import com.hello.hello_store_service.model.entity.user.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?
}