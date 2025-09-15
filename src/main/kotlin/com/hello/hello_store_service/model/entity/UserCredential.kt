package com.hello.hello_store_service.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "user_credentials")
data class UserCredential(
    @Id
    val id: String? = null,
    val passwordHash: String,
    val salt: String? = null,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
