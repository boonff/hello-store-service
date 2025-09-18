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
    val counts: List<CountDto> = emptyList(),
    val orderStats: List<OrderStatDto> = emptyList(),
    val customerService: CustomerServiceDto? = null,
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
                counts = user.counts.map { CountDto.from(it) },
                orderStats = user.orderStats.map { OrderStatDto.from(it) },
                customerService = user.customerService?.let { CustomerServiceDto.from(it) },
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

data class CountDto(
    val type: String,
    val num: Int
) {
    companion object {
        fun from(count: Count) = CountDto(type = count.type, num = count.num)
    }
}

data class OrderStatDto(
    val tabType: Int,
    val orderNum: Int
) {
    companion object {
        fun from(stat: OrderStat) = OrderStatDto(tabType = stat.tabType, orderNum = stat.orderNum)
    }
}

data class CustomerServiceDto(
    val servicePhone: String,
    val serviceTimeDuration: String
) {
    companion object {
        fun from(service: CustomerService) = CustomerServiceDto(
            servicePhone = service.servicePhone,
            serviceTimeDuration = service.serviceTimeDuration
        )
    }
}
