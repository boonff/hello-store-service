package com.hello.hello_store_service.repository.activity


import com.hello.hello_store_service.model.entity.activity.CouponEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponRepository : MongoRepository<CouponEntity, String> {
    fun findByTag(tag: String): List<CouponEntity>
}