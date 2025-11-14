package com.hello.hello_store_service.application.coupon

import com.hello.hello_store_service.application.coupon.model.CouponInstance
import com.hello.hello_store_service.application.coupon.rule.CouponRuleFactory
import org.springframework.stereotype.Service


@Service
class OrderCouponRule(
    private val userCouponService: CouponService,
    private val couponRuleFactory: CouponRuleFactory
) {

    fun discountCoupons(
        uid: String,
        couponIds: List<String>,
        originalPrice: Int
    ): Int {
        val userCoupons = fetchUserCoupons(uid, couponIds)
        return calculateTotalDiscount(userCoupons, originalPrice)
    }

    private fun calculateTotalDiscount(userCoupons: List<CouponInstance>, originalPrice: Int): Int {
        return userCoupons.sumOf { userCoupon ->
            val couponRule = couponRuleFactory.build(userCoupon.type)
            couponRule.calculateDiscount(userCoupon, originalPrice)
        }
    }

    private fun fetchUserCoupons(uid: String, couponIds: List<String>): List<CouponInstance> =
        userCouponService.fetchByCouponIds(uid, couponIds)
}