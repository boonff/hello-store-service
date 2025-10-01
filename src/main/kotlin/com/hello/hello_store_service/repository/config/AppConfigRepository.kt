package com.hello.hello_store_service.repository.config

import com.hello.hello_store_service.model.entity.config.AppConfig
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AppConfigRepository : MongoRepository<AppConfig, String> {
    fun findByConfigId(configId: String): AppConfig?
}