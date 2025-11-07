package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.model.entity.PromotionEntity
import com.hello.hello_store_service.data.model.entity.PromotionStatus
import com.hello.hello_store_service.data.repository.PromotionRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PromotionDataService(private val promotionRepository: PromotionRepository) {

    fun savePromotion(promotion: PromotionEntity): PromotionEntity {
        return promotionRepository.save(promotion)
    }

    fun fetchById(id: String): PromotionEntity? {
        return promotionRepository.findById(id).orElse(null)
    }

    fun deleteById(id: String) {
        promotionRepository.deleteById(id)
    }

    fun fetchByStore(storeId: String): PromotionEntity {
        return promotionRepository.findByStoreId(storeId)
    }

    // 根据状态查找促销活动
    fun fetchByStatus(status: Int): List<PromotionEntity> {
        return promotionRepository.findByStatus(status)
    }

    // 获取当前有效的促销活动（startAt <= now <= endAt）
    fun fetchCurrentPromotions(): List<PromotionEntity> {
        val now = Instant.now().toEpochMilli()
        return promotionRepository.findByStartAtLessThanEqualAndEndAtGreaterThanEqual(now, now)
    }

    // 根据促销类型筛选
    fun fetchPromotionsByType(type: PromotionStatus): List<PromotionEntity> {
        return promotionRepository.findAll().filter { it.rule.type == type }
    }

    // 根据标题关键字模糊搜索
    fun findPromotionsByTitle(keyword: String): List<PromotionEntity> {
        return promotionRepository.findByTitleContaining(keyword)
    }
}