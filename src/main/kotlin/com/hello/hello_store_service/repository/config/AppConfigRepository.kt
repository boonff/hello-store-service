package com.hello.hello_store_service.repository.config

import com.hello.hello_store_service.model.entity.config.AppConfigEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AppConfigRepository : MongoRepository<AppConfigEntity, String> {
    fun findByConfigId(configId: String): AppConfigEntity?
}