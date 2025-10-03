package com.hello.hello_store_service.service.activity

import com.hello.hello_store_service.model.entity.activity.Promotion
import com.hello.hello_store_service.model.entity.activity.PromotionStatus
import com.hello.hello_store_service.repository.activity.PromotionRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PromotionService(private val promotionRepository: PromotionRepository) {

    // 新增或更新促销活动
    fun savePromotion(promotion: Promotion): Promotion {
        return promotionRepository.save(promotion)
    }

    // 根据ID查找促销活动
    fun getPromotionById(id: String): Promotion? {
        return promotionRepository.findById(id).orElse(null)
    }

    // 删除促销活动
    fun deletePromotion(id: String) {
        promotionRepository.deleteById(id)
    }

    // 获取某个店铺的所有促销活动
    fun getPromotionsByStore(storeId: String): List<Promotion> {
        return promotionRepository.findByStoreId(storeId)
    }

    fun getPromotionsByStores(storeIds: List<String>): List<Promotion> {
        if (storeIds.isEmpty()) return emptyList()
        return promotionRepository.findByStoreIdIn(storeIds)
    }

    // 根据状态查找促销活动
    fun getPromotionsByStatus(status: Int): List<Promotion> {
        return promotionRepository.findByStatus(status)
    }

    // 获取当前有效的促销活动（startAt <= now <= endAt）
    fun getCurrentPromotions(): List<Promotion> {
        val now = Instant.now().toEpochMilli()
        return promotionRepository.findByStartAtLessThanEqualAndEndAtGreaterThanEqual(now, now)
    }

    // 根据促销类型筛选
    fun getPromotionsByType(type: PromotionStatus): List<Promotion> {
        return promotionRepository.findAll().filter { it.rule.type == type }
    }

    // 根据标题关键字模糊搜索
    fun searchPromotionsByTitle(keyword: String): List<Promotion> {
        return promotionRepository.findByTitleContaining(keyword)
    }
}
