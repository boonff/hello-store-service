package com.hello.hello_store_service.repository.activity

import com.hello.hello_store_service.model.entity.activity.Promotion
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository : MongoRepository<Promotion, String> {
    // 使用 @Query 指定查询字段
    @Query("{ 'storeId': ?0 }")
    fun findByStoreId(storeId: String): List<Promotion>
}

