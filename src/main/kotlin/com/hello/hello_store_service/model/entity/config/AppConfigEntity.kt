package com.hello.hello_store_service.model.entity.config

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "app_config")
data class AppConfigEntity(
    @Id
    val id: String? = null,
    val configId: String = "appConfig",
    val customerService: CustomerService
)

data class CustomerService(
    val servicePhone: String,       // 客服电话
    val serviceTimeDuration: String // 服务时间
)