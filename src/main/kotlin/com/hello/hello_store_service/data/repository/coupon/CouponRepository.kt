package com.hello.hello_store_service.data.repository.coupon


import com.hello.hello_store_service.data.model.composite.UserCoupon
import com.hello.hello_store_service.data.model.entity.CouponEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponRepository : MongoRepository<CouponEntity, String>, CouponRepositoryCustom {
    fun findByTag(tag: String): List<CouponEntity>
}

interface CouponRepositoryCustom{
    fun findUserCouponDTOs(username: String): List<UserCoupon>
}
