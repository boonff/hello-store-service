package com.hello.hello_store_service.application.coupon

import com.hello.hello_store_service.data.model.record.UserCouponRecord
import org.springframework.stereotype.Component
import java.time.LocalDateTime

interface UserCouponEvaluator {
    /**
     * 将数据库记录计算成业务层的优惠券对象
     */
    fun evaluate(record: UserCouponRecord): UserCouponModel
}


@Component
class DefaultUserCouponEvaluator : UserCouponEvaluator {
    override fun evaluate(record: UserCouponRecord): UserCouponModel {
        val coupon = record.coupon
        val failureAt = record.receivedAt.plusDays(coupon.validDays.toLong())

        val status = when {
            record.isUsed -> CouponStatus.UseLess
            LocalDateTime.now().isAfter(failureAt) -> CouponStatus.Disabled
            else -> CouponStatus.Default
        }

        return UserCouponModel(
            couponId = coupon.couponId,
            title = coupon.title,
            description = coupon.description,
            type = coupon.type,
            tag = coupon.tag,
            threshold = coupon.threshold,
            discount = coupon.discount,
            discountRate = coupon.discountRate,
            receivedAt = record.receivedAt,
            failureAt = failureAt,
            status = status
        )
    }
}
