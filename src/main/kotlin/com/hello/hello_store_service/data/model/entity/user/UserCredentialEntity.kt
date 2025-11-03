package com.hello.hello_store_service.data.model.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "user_credentials")
data class UserCredentialEntity(
    @Id
    val username: String? = null,
    val passwordHash: String,
    val salt: String? = null,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
