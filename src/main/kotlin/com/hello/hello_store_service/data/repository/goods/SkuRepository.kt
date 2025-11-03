package com.hello.hello_store_service.data.repository.goods

import com.hello.hello_store_service.data.model.entity.goods.SkuEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SkuRepository : MongoRepository<SkuEntity, String> {

    // 查找某个商品（spuId）下的所有 SKU
    fun findBySpuId(spuId: String): List<SkuEntity>

    // 根据多个 spuId 查找 SKU（比如批量查询商品下的所有 SKU）
    fun findAllBySpuIdIn(spuIds: List<String>): List<SkuEntity>

    // 按库存过滤（找库存不足的）
    fun findByStockInfoStockQuantityLessThan(threshold: Int): List<SkuEntity>
}
