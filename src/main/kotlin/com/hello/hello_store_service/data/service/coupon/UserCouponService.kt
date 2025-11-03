package com.hello.hello_store_service.data.service.coupon

import com.hello.hello_store_service.data.model.composite.UserCoupon
import com.hello.hello_store_service.data.model.entity.CouponEntity
import com.hello.hello_store_service.data.repository.coupon.CouponRepository
import com.hello.hello_store_service.web.trans.business.coupon.CouponCalculator
import com.hello.hello_store_service.web.trans.business.coupon.CouponDomain
import com.hello.hello_store_service.web.trans.view.coupon.CouponResultList
import com.hello.hello_store_service.web.trans.view.coupon.UserCouponView
import org.springframework.stereotype.Service

@Service
class UserCouponService(
    private val couponRepository: CouponRepository,
    private val couponCalculator: CouponCalculator
) {
    fun fetchUserCoupon(username: String): List<UserCoupon> =
        couponRepository.findUserCouponDTOs(username)

    fun couponResultList(username: String) =
        CouponResultList.Companion.from(fetchUserCouponBOs(username))

    fun userCouponViews(username: String) =
        fetchUserCouponBOs(username).map { userCouponBO -> UserCouponView.Companion.from(userCouponBO) }

    fun fetchCouponById(couponId: String): CouponEntity? =
        couponRepository.findById(couponId).orElse(null)

    private fun fetchUserCouponBOs(username: String): List<CouponDomain> {
        return fetchUserCoupon(username).map { userCoupon ->
            CouponDomain(userCoupon)
        }
    }

    //TODO 测试是否可以被删除
    fun fetchStatusCoupons(username: String, status: String): List<UserCouponView> {
        return userCouponViews(username).filter { it.status == status }

    }
}