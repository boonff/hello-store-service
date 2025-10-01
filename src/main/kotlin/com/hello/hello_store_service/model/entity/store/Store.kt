package com.hello.hello_store_service.model.entity.store

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("stores")
data class Store(
    @Id
    val storeId: String? = null,
    val storeName: String,
    val storeStatus: Int,
)