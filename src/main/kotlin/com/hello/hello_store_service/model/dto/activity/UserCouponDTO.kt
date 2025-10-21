package com.hello.hello_store_service.model.dto.activity

import com.hello.hello_store_service.model.entity.activity.Coupon
import com.hello.hello_store_service.model.entity.activity.CouponType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class UserCouponDTO(
    val key: String,
    val status: String,
    val type: Int,
    val value: Double?,
    val tag: String?,
    val desc: String,
    val base: Double?,
    val title: String,
    val timeLimit: String,
    val currency: String = "¥"
) {
    companion object {
        fun from(coupon: Coupon, isUsed: Boolean, receivedAt: LocalDateTime): UserCouponDTO {
            return UserCouponDTO(
                key = coupon.couponId,
                status = coupon.getStatus(isUsed, receivedAt),
                type = coupon.getType(),
                value = coupon.getValue(),
                tag = coupon.tag,
                desc = coupon.description,
                base = coupon.threshold,
                title = coupon.title,
                timeLimit = coupon.getTimeLimit(receivedAt),
                currency = "¥"
            )
        }

        private fun Coupon.getStatus(isUsed: Boolean, receivedAt: LocalDateTime): String {
            if (isUsed) return CouponStatus.UseLess.typeName
            val expireAt = receivedAt.plusDays(validDays.toLong())
            return if (LocalDateTime.now().isBefore(expireAt))
                CouponStatus.Default.typeName
            else
                CouponStatus.Disabled.typeName


        }

        private fun Coupon.getType(): Int = when (type) {
            CouponType.PriceOff -> 1
            CouponType.Discount -> 2
        }

        private fun Coupon.getValue(): Double? = when (type) {
            CouponType.PriceOff -> discount
            CouponType.Discount -> discountRate
        }

        fun Coupon.getTimeLimit(receivedAt: LocalDateTime): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
            val start = receivedAt.format(formatter)
            val end = receivedAt
                .plusDays(validDays.toLong())
                .format(formatter)

            return "$start - $end"
        }

    }
}

private enum class CouponStatus(val typeName: String) {
    Default("default"),
    UseLess("useless"),
    Disabled("disabled"),
}