package com.hello.hello_store_service.service.activity

import com.hello.hello_store_service.model.business.activity.CouponBO
import com.hello.hello_store_service.model.entity.activity.CouponEntity
import com.hello.hello_store_service.model.view.activity.UserCouponView
import com.hello.hello_store_service.repository.activity.CouponRepository
import com.hello.hello_store_service.repository.activity.UserCouponRepository
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val couponRepository: CouponRepository,
    private val userCouponRepository: UserCouponRepository
) {
    fun fetchCouponById(couponId: String): CouponEntity? =
        couponRepository.findById(couponId).orElse(null)

    fun fetchUserCoupons(username: String): List<UserCouponView> {
        val userCoupons = userCouponRepository.findByUsername(username)
        val coupons = couponRepository.findAll().associateBy { it.couponId }
        return userCoupons.mapNotNull { uc ->
            coupons[uc.couponId]?.let { coupon ->
                val bo = CouponBO(coupon, uc.isUsed, uc.receivedAt)
                UserCouponView.from(bo)
            }
        }
    }

    fun fetchStatusCoupons(username: String, status: String): List<UserCouponView> {
        return fetchUserCoupons(username).filter { it.status == status }

    }
}