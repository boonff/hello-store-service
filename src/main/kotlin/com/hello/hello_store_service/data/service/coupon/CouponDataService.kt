package com.hello.hello_store_service.data.service.coupon

import com.hello.hello_store_service.data.entity.coupon.CouponEntity
import com.hello.hello_store_service.data.repository.coupon.CouponRepository
import org.springframework.stereotype.Service

@Service
class CouponDataService(
    private val couponRepository: CouponRepository,
) {
    fun fetchById(couponId: String): CouponEntity? =
        couponRepository.findById(couponId).orElse(null)

    fun fetchByIds(couponIds: List<String>): List<CouponEntity> =
        couponIds.mapNotNull { couponId ->
            fetchById(couponId)
        }
}