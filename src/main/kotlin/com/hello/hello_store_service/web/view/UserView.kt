package com.hello.hello_store_service.web.view

import com.hello.hello_store_service.data.entity.user.MerchantInfo
import com.hello.hello_store_service.data.entity.user.UserEntity
import com.hello.hello_store_service.data.entity.user.UserType

data class UserView(
    val username: String,
    val nickName: String,
    val avatarUrl: String,
    val phoneNumber: String,
    val gender: Int,
    val userType: UserType,
    val merchantInfo: MerchantInfo? = null,
    val createdAt: Long,
    val updatedAt: Long
) {
    companion object {
        fun from(user: UserEntity): UserView {
            return UserView(
                username = user.username,
                nickName = user.nickName,
                avatarUrl = user.avatarUrl,
                phoneNumber = user.phoneNumber,
                gender = user.gender.code,
                userType = user.userType,
                merchantInfo = user.merchantInfo,
                createdAt = user.createdAt.toEpochMilli(),
                updatedAt = user.updatedAt.toEpochMilli()
            )
        }
    }
}