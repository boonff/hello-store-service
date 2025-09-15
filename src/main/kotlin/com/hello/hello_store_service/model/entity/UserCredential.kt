package com.hello.hello_store_service.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "user_credentials")
data class UserCredential(
    @Id
    val id: String? = null,      // 对应 User.id
    val passwordHash: String,    // bcrypt/argon2 hash
    val salt: String? = null,    // 可选，如果 hash 已包含 salt，可不存
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
