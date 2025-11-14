package com.hello.hello_store_service.data.entity.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * 用户集合
 */
@Document(collection = "users")
data class UserEntity(
    @Id val uid: String? = null,
    @Indexed(unique = true)
    val username: String,
    val nickName: String,
    val avatarUrl: String,
    val phoneNumber: String,
    val gender: Gender, // 0未知, 1男, 2女
    val userType: UserType,

    val merchantInfo: MerchantInfo? = null,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)

enum class UserType {
    ADMIN,
    MERCHANT,
    SELLER
}

enum class Gender(val code: Int) {
    Other(0),
    Male(1),
    Female(2)
}

//TODO 需要删除
data class MerchantInfo(
    val shopName: String,
    val shopAddress: String,
    val licenseNumber: String
)