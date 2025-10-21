package com.hello.hello_store_service.repository.activity


import com.hello.hello_store_service.model.entity.activity.Coupon
import com.hello.hello_store_service.model.entity.activity.CouponType
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CouponRepository : MongoRepository<Coupon, String> {
    fun findByTag(tag: String): List<Coupon>
}