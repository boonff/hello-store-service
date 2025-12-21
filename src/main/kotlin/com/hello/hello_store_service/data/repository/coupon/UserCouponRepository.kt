package com.hello.hello_store_service.data.repository.coupon


import com.hello.hello_store_service.data.entity.coupon.UserCouponEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCouponRepository : MongoRepository<UserCouponEntity, String> {
    fun findByUid(uid: String): List<UserCouponEntity>
    fun findByUidAndCouponId(uid: String, couponId: String): UserCouponEntity?
}