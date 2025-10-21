package com.hello.hello_store_service.repository.activity


import com.hello.hello_store_service.model.entity.activity.UserCoupon
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserCouponRepository : MongoRepository<UserCoupon, String> {
    fun findByUsername(username: String): List<UserCoupon>
    fun findByUsernameAndCouponId(username: String, couponId: String): UserCoupon?
}