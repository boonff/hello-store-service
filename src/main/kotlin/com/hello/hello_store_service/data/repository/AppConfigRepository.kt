package com.hello.hello_store_service.data.repository

import com.hello.hello_store_service.data.model.entity.AppConfigEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AppConfigRepository : MongoRepository<AppConfigEntity, String> {
    fun findByConfigId(configId: String): AppConfigEntity?
}