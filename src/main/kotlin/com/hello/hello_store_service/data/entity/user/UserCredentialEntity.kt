package com.hello.hello_store_service.data.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "user_credentials")
data class UserCredentialEntity(
    @Id val uid: String?,
    val passwordHash: String,
    val salt: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)
