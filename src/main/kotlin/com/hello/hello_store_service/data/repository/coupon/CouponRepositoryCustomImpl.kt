package com.hello.hello_store_service.data.repository.coupon

import com.hello.hello_store_service.data.entity.coupon.CouponEntity
import com.hello.hello_store_service.data.entity.coupon.UserCouponEntity
import com.hello.hello_store_service.data.model.UserCouponModel
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class CouponRepositoryCustomImpl(
    private val mongoTemplate: MongoTemplate
) : CouponRepositoryCustom {

    override fun findUserCouponModel(uid: String): List<UserCouponModel> {
        val userCoupons = mongoTemplate.find(
            Query.query(Criteria.where("uid").`is`(uid)),
            UserCouponEntity::class.java,
            "user_coupons"
        )

        val couponIds = userCoupons.map { it.couponId }
        val coupons = mongoTemplate.find(
            Query.query(Criteria.where("couponId").`in`(couponIds)),
            CouponEntity::class.java,
            "coupons"
        ).associateBy { it.couponId }

        return userCoupons.mapNotNull { userCoupon ->
            coupons[userCoupon.couponId]?.let { coupon ->
                UserCouponModel(
                    coupon = coupon,
                    isUsed = userCoupon.isUsed,
                    receivedAt = userCoupon.receivedAt
                )
            }
        }
    }
}
