package com.hello.hello_store_service.web.trans.business.coupon


import com.hello.hello_store_service.data.model.entity.CouponEntity
import com.hello.hello_store_service.data.model.entity.CouponStatus
import com.hello.hello_store_service.data.model.entity.CouponType
import com.hello.hello_store_service.data.model.composite.UserCoupon
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CouponCalculator(val userCoupon: UserCoupon) {
    private val coupon: CouponEntity = userCoupon.coupon
    val startTime = userCoupon.receivedAt
    val endTime: LocalDateTime = userCoupon.receivedAt.plusDays(coupon.validDays.toLong())
    val status: CouponStatus = when {
        userCoupon.isUsed -> CouponStatus.UseLess
        LocalDateTime.now().isAfter(endTime) -> CouponStatus.Disabled
        else -> CouponStatus.Default
    }

    /**
     * 计算优惠金额
     * @param originalPrice 原始价格（单位：分）
     * @return 实际优惠金额（单位：分）
     */
    fun calculateDiscount(originalPrice: Int): Int {
        if (!isCouponValid(originalPrice)) return 0
        return when (coupon.type) {
            CouponType.PriceOff -> {
                coupon.discount ?: 0
            }

            CouponType.Discount -> {
                val rate = coupon.discountRate ?: 1f
                val discounted = (originalPrice * (1 - rate)).toInt()
                discounted.coerceAtLeast(0)
            }
        }
    }


    private fun isCouponValid(originalPrice: Int): Boolean {
        val coupon = userCoupon.coupon
        val now = LocalDateTime.now()
        // 已使用的优惠券不可用
        return if (userCoupon.isUsed) false
        // 判断是否过期
        else if (now.isAfter(endTime)) false
        // 判断门槛（如果有设置）
        else if (originalPrice < coupon.threshold) false
        else true
    }
}
