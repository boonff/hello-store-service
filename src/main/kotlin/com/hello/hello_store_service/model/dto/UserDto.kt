package com.hello.hello_store_service.model.dto

import com.hello.hello_store_service.model.entity.*

data class UserDto(
    val id: String?,
    val username: String,
    val nickName: String,
    val avatarUrl: String,
    val phoneNumber: String,
    val gender: Int,
    val userType: UserType,
    val merchantInfo: MerchantInfoDto? = null,
    val countData: List<CountDataDto> = emptyList(),
    val orderTagInfo: List<OrderTagInfoDto> = emptyList(),
    val createdAt: Long,
    val updatedAt: Long
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                id = user.id,
                username = user.username,
                nickName = user.nickName,
                avatarUrl = user.avatarUrl,
                phoneNumber = user.phoneNumber,
                gender = user.gender,
                userType = user.userType,
                merchantInfo = user.merchantInfo?.let { MerchantInfoDto.from(it) },
                countData = user.countData.map { CountDataDto.from(it) },
                orderTagInfo = user.orderTagInfo.map { OrderTagInfoDto.from(it) },
                createdAt = user.createdAt.toEpochMilli(),
                updatedAt = user.updatedAt.toEpochMilli()
            )
        }
    }
}

data class MerchantInfoDto(
    val shopName: String,
    val shopAddress: String,
    val licenseNumber: String
) {
    companion object {
        fun from(info: MerchantInfo) = MerchantInfoDto(
            shopName = info.shopName,
            shopAddress = info.shopAddress,
            licenseNumber = info.licenseNumber
        )
    }
}

data class CountDataDto(
    val type: String,
    val name: String,
    val num: Int
) {
    companion object {
        fun from(countData: CountData) = CountDataDto(type = countData.type, name = countData.name, num = countData.num)
    }
}

data class OrderTagInfoDto(
    val tabType: Int,
    val orderNum: Int
) {
    companion object {
        fun from(stat: OrderTagInfo) = OrderTagInfoDto(tabType = stat.tabType, orderNum = stat.orderNum)
    }
}
