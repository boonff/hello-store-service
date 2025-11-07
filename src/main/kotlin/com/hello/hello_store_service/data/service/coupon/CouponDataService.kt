package com.hello.hello_store_service.data.service.coupon

import com.hello.hello_store_service.data.model.entity.coupon.CouponEntity
import com.hello.hello_store_service.data.repository.coupon.CouponRepository
import org.springframework.stereotype.Service
import kotlin.collections.associateBy

@Service
class CouponDataService(
    private val couponRepository: CouponRepository,
) {
    fun fetchCouponById(couponId: String): CouponEntity? =
        couponRepository.findById(couponId).orElse(null)

    fun fetchCouponByIds(couponIds: List<String>): List<CouponEntity> =
        couponIds.mapNotNull { couponId ->
            fetchCouponById(couponId)
        }

    fun couponsById(): Map<String, CouponEntity> =
        couponRepository.findAll().associateBy { it.couponId }
}