package com.hello.hello_store_service.repository

import com.hello.hello_store_service.model.entity.Store
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : MongoRepository<Store, String> {

    // 根据店铺状态查询
    fun findByStoreStatus(storeStatus: Int): List<Store>

    // 根据店铺名字模糊查询
    fun findByStoreNameContaining(storeName: String): List<Store>

    // 根据店铺名字精确查询
    fun findByStoreName(storeName: String): Store?

    // 根据店铺ID判断是否存在
    fun existsByStoreId(storeId: String): Boolean

    // 根据状态统计店铺数量
    fun countByStoreStatus(storeStatus: Int): Long
}
