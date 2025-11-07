package com.hello.hello_store_service.data.repository.coupon


import com.hello.hello_store_service.data.model.record.UserCouponRecord
import com.hello.hello_store_service.data.model.entity.coupon.CouponEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponRepository : MongoRepository<CouponEntity, String>, CouponRepositoryCustom {
    fun findByTag(tag: String): List<CouponEntity>
}

interface CouponRepositoryCustom{
    fun findUserCouponRecord(username: String): List<UserCouponRecord>
}
