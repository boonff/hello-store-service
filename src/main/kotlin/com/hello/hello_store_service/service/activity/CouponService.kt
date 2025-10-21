package com.hello.hello_store_service.service.activity

import com.hello.hello_store_service.model.dto.activity.UserCouponDTO
import com.hello.hello_store_service.repository.activity.CouponRepository
import com.hello.hello_store_service.repository.activity.UserCouponRepository
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val couponRepository: CouponRepository,
    private val userCouponRepository: UserCouponRepository
) {
    fun getUserCoupons(username: String): List<UserCouponDTO> {
        val userCoupons = userCouponRepository.findByUsername(username)
        val coupons = couponRepository.findAll().associateBy { it.couponId }
        return userCoupons.mapNotNull { uc ->
            coupons[uc.couponId]?.let { coupon ->
                UserCouponDTO.from(coupon, uc.isUsed, uc.receivedAt)
            }
        }
    }

    fun getStatusCoupons(username: String, status: String): List<UserCouponDTO> {
        return getUserCoupons(username).filter { it.status == status }

    }
}