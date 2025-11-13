package com.hello.hello_store_service.data.service.coupon

import com.hello.hello_store_service.data.entity.coupon.UserCouponEntity
import com.hello.hello_store_service.data.model.UserCouponModel
import com.hello.hello_store_service.data.repository.coupon.CouponRepository
import com.hello.hello_store_service.data.repository.coupon.UserCouponRepository
import org.springframework.stereotype.Service

@Service
class UserCouponDataService(
    private val userCouponRepository: UserCouponRepository,
    private val couponRepository: CouponRepository,
) {
    fun fetchByUid(uid: String): List<UserCouponModel> {
        return couponRepository.findUserCouponModel(uid)
    }

    fun fetchById(uid: String, couponId: String): UserCouponModel? =
        fetchByUid(uid).firstOrNull { it.coupon.couponId == couponId }

    fun fetchByIds(uid: String, couponIds: List<String>): List<UserCouponModel> =
        fetchByUid(uid).filter { it.coupon.couponId in couponIds }


}