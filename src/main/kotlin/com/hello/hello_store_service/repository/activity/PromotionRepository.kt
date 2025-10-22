package com.hello.hello_store_service.repository.activity

import com.hello.hello_store_service.model.entity.activity.PromotionEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository : MongoRepository<PromotionEntity, String> {

    // 根据店铺ID查找促销活动
    fun findByStoreId(storeId: String): List<PromotionEntity>

    fun findByStoreIdIn(storeIds: List<String>): List<PromotionEntity>

    // 根据状态查找促销活动
    fun findByStatus(status: Int): List<PromotionEntity>

    // 根据开始时间和结束时间查找有效的促销活动
    fun findByStartAtLessThanEqualAndEndAtGreaterThanEqual(now: Long, now2: Long): List<PromotionEntity>

    // 根据标题模糊查询
    fun findByTitleContaining(title: String): List<PromotionEntity>
}
