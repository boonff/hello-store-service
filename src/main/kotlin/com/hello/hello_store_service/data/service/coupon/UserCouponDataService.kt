package com.hello.hello_store_service.data.service.coupon

import com.hello.hello_store_service.data.model.record.UserCouponRecord
import com.hello.hello_store_service.data.repository.coupon.CouponRepository
import com.hello.hello_store_service.data.repository.coupon.UserCouponRepository
import org.springframework.stereotype.Service

@Service
class UserCouponDataService(
    private val userCouponRepository: UserCouponRepository,
    private val couponRepository: CouponRepository,
) {
    fun fetchByUsername(username: String): List<UserCouponRecord> {
        return couponRepository.findUserCouponRecord(username)
    }

    fun fetchByCouponIds(username: String, couponIds: List<String>): List<UserCouponRecord> =
        fetchByUsername(username).filter { it.coupon.couponId in couponIds }

    fun fetchByCouponId(username: String, couponId: String): UserCouponRecord? =
        fetchByUsername(username).firstOrNull { it.coupon.couponId in couponId }
}