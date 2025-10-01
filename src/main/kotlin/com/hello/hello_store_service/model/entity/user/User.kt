package com.hello.hello_store_service.model.entity.user

import com.hello.hello_store_service.model.entity.config.CustomerService
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * 用户集合
 */
@Document(collection = "users")
data class User(
    @Id
    val username: String,
    val nickName: String,
    val avatarUrl: String,
    val phoneNumber: String,
    val gender: Int, // 0未知, 1男, 2女
    val userType: UserType,

    val merchantInfo: MerchantInfo? = null, // 只有商家/卖家才有

    val countData: List<CountData> = emptyList(),

    val orderTagInfo: List<OrderTagInfo> = emptyList(),

    val customerService: CustomerService? = null,

    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)

enum class UserType {
    ADMIN,
    MERCHANT,
    SELLER
}

data class MerchantInfo(
    val shopName: String,
    val shopAddress: String,
    val licenseNumber: String
)

data class CountData(
    val type: String,
    val name: String,
    val num: Int
)

data class OrderTagInfo(
    val tabType: Int,
    val orderNum: Int
)