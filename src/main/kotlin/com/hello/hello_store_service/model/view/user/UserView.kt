package com.hello.hello_store_service.model.view.user

import com.hello.hello_store_service.model.entity.user.CountData
import com.hello.hello_store_service.model.entity.user.MerchantInfo
import com.hello.hello_store_service.model.entity.user.OrderTagInfo
import com.hello.hello_store_service.model.entity.user.User
import com.hello.hello_store_service.model.entity.user.UserType

data class UserView(
    val username: String,
    val nickName: String,
    val avatarUrl: String,
    val phoneNumber: String,
    val gender: Int,
    val userType: UserType,
    val merchantInfo: MerchantInfo? = null,
    val countData: List<CountData> = emptyList(),
    val orderTagInfo: List<OrderTagInfo> = emptyList(),
    val createdAt: Long,
    val updatedAt: Long
) {
    companion object {
        fun from(user: User): UserView {
            return UserView(
                username = user.username,
                nickName = user.nickName,
                avatarUrl = user.avatarUrl,
                phoneNumber = user.phoneNumber,
                gender = user.gender,
                userType = user.userType,
                merchantInfo = user.merchantInfo,
                countData = user.countData,
                orderTagInfo = user.orderTagInfo,
                createdAt = user.createdAt.toEpochMilli(),
                updatedAt = user.updatedAt.toEpochMilli()
            )
        }
    }
}