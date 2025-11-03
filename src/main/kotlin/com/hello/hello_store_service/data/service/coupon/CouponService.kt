package com.hello.hello_store_service.data.service.coupon

import com.hello.hello_store_service.data.model.entity.CouponEntity
import com.hello.hello_store_service.data.repository.coupon.CouponRepository
import org.springframework.stereotype.Service
import kotlin.collections.associateBy

@Service
class CouponService(
    private val couponRepository: CouponRepository,
) {
    fun couponsById(): Map<String, CouponEntity> =
        couponRepository.findAll().associateBy { it.couponId }
}