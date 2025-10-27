package com.hello.hello_store_service.model.business.activity

import com.hello.hello_store_service.model.entity.activity.CouponEntity
import com.hello.hello_store_service.model.entity.activity.CouponType
import java.time.LocalDateTime

data class CouponBO(
    val coupon: CouponEntity,
    val isUsed: Boolean,
    val receivedAt: LocalDateTime
) {
    fun getStatus(): String {
        if (isUsed) return CouponStatus.UseLess.typeName
        val expireAt = receivedAt.plusDays(coupon.validDays.toLong())
        return if (LocalDateTime.now().isBefore(expireAt))
            CouponStatus.Default.typeName
        else
            CouponStatus.Disabled.typeName
    }

    fun getType(): Int = when (coupon.type) {
        CouponType.PriceOff -> 1
        CouponType.Discount -> 2
    }

    fun getValue(): Double? = when (coupon.type) {
        CouponType.PriceOff -> coupon.discount?.toDouble()
        CouponType.Discount -> coupon.discountRate?.toDouble()
    }

    fun getTimeLimit(): Pair<LocalDateTime, LocalDateTime> {
        val start = receivedAt
        val end = receivedAt.plusDays(coupon.validDays.toLong())
        return start to end
    }
}

private enum class CouponStatus(val typeName: String) {
    Default("default"),
    UseLess("useless"),
    Disabled("disabled"),
}
