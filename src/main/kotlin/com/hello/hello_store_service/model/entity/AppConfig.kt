package com.hello.hello_store_service.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "app_config")
data class AppConfig(
    @Id
    val id: String = "appConfig",
    val customerService: CustomerService
)

data class CustomerService(
    val servicePhone: String,       // 客服电话
    val serviceTimeDuration: String // 服务时间
)