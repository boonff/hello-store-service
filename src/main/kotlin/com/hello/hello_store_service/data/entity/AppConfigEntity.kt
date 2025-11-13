package com.hello.hello_store_service.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

const val AppCONFIG = "appConfig"

@Document(collection = "app_config")
data class AppConfigEntity(
    @Id val id: String? = null,
    val configId: String = AppCONFIG,
    val customerService: CustomerService,
    val storeInfo: StoreInfo,
    val paymentInfo: PaymentInfo
)


data class StoreInfo(
    val name: String,           // 商店名称
    val logoUrl: String? = null,       // 商店Logo
    val description: String? = null,   // 商店简介
    val address: String? = null,       // 地址（可选）
    val contactEmail: String? = null   // 邮箱（可选）
)

data class PaymentInfo(
    val wechatMchId: String,     // 微信商户号
    val wechatApiKey: String,    // 微信API密钥（建议加密存储）
    val notifyUrl: String        // 支付回调地址
)

data class CustomerService(
    val servicePhone: String,       // 客服电话
    val serviceTimeDuration: String // 服务时间
)