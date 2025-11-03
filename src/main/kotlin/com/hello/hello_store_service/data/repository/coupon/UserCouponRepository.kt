package com.hello.hello_store_service.data.repository.coupon


import com.hello.hello_store_service.data.model.entity.UserCouponEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCouponRepository : MongoRepository<UserCouponEntity, String> {
    fun findByUsername(username: String): List<UserCouponEntity>
    fun findByUsernameAndCouponId(username: String, couponId: String): UserCouponEntity?
}