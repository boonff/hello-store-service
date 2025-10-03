package com.hello.hello_store_service.repository.activity

import com.hello.hello_store_service.model.entity.activity.Promotion
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PromotionRepository : MongoRepository<Promotion, String> {

    // 根据店铺ID查找促销活动
    fun findByStoreId(storeId: String): List<Promotion>

    fun findByStoreIdIn(storeIds: List<String>): List<Promotion>

    // 根据状态查找促销活动
    fun findByStatus(status: Int): List<Promotion>

    // 根据开始时间和结束时间查找有效的促销活动
    fun findByStartAtLessThanEqualAndEndAtGreaterThanEqual(now: Long, now2: Long): List<Promotion>

    // 根据标题模糊查询
    fun findByTitleContaining(title: String): List<Promotion>
}
