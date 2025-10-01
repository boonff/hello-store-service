package com.hello.hello_store_service.repository

import com.hello.hello_store_service.model.entity.Promotion
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository : MongoRepository<Promotion, String> {

    // 根据 promotionId 查询单个促销
    fun findByPromotionId(promotionId: String): Promotion?

    // 根据 promotionCode 查询促销
    fun findByPromotionCode(promotionCode: String): List<Promotion>

    // 删除指定 promotionId 的促销
    fun deleteByPromotionId(promotionId: String)
}
