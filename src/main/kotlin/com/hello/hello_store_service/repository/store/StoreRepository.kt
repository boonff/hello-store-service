package com.hello.hello_store_service.repository.store

import com.hello.hello_store_service.model.entity.store.Store
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : MongoRepository<Store, String> {
    // 根据店铺名称查找
    fun findByStoreName(storeName: String): List<Store>

    fun findByStoreId(storeId: String): List<Store>

    // 根据多个店铺ID查找
    fun findByStoreIdIn(storeIds: List<String>): List<Store>

    // 根据店铺状态查找
    fun findByStoreStatus(storeStatus: Int): List<Store>

    // 根据类型查找
    fun findByStoreType(storeType: String): List<Store>

    // 根据是否支持配送查找
    fun findByDeliverySupported(deliverySupported: Boolean): List<Store>

    // 根据评分大于某值查找
    fun findByRatingGreaterThan(minRating: Double): List<Store>
}
