package com.hello.hello_store_service.data.repository

import com.hello.hello_store_service.data.model.entity.StoreEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : MongoRepository<StoreEntity, String> {
    // 根据店铺名称查找
    fun findByStoreName(storeName: String): List<StoreEntity>

    fun findByStoreId(storeId: String): List<StoreEntity>

    // 根据多个店铺ID查找
    fun findByStoreIdIn(storeIds: List<String>): List<StoreEntity>

    // 根据店铺状态查找
    fun findByStoreStatus(storeStatus: Int): List<StoreEntity>

    // 根据类型查找
    fun findByStoreType(storeType: String): List<StoreEntity>

    // 根据是否支持配送查找
    fun findByDeliverySupported(deliverySupported: Boolean): List<StoreEntity>

    // 根据评分大于某值查找
    fun findByRatingGreaterThan(minRating: Double): List<StoreEntity>
}