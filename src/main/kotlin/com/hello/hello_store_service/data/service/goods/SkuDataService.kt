package com.hello.hello_store_service.data.service.goods

import com.hello.hello_store_service.data.entity.goods.SkuEntity
import com.hello.hello_store_service.data.repository.goods.SkuRepository
import com.hello.hello_store_service.web.view.goods.SkuDetail
import org.springframework.stereotype.Service

@Service
class SkuDataService(
    private val skuRepository: SkuRepository
) {
    fun fetchDetail(skuId: String): SkuDetail? = fetchById(skuId)?.let { skuEntity ->
        SkuDetail.from(skuEntity)
    }

    fun fetchDetails(skuId: String): List<SkuDetail> = fetchBySpuId(skuId).mapNotNull { skuEntity ->
        SkuDetail.from(skuEntity)
    }

    fun fetchAll(): List<SkuEntity> = skuRepository.findAll()

    fun fetchById(skuId: String): SkuEntity? = skuRepository.findById(skuId).orElse(null)

    fun fetchBySpuId(spuId: String): List<SkuEntity> = skuRepository.findBySpuId(spuId)

    fun fetchByIds(skuIds: List<String>): List<SkuEntity> = skuRepository.findAllById(skuIds)

    // 获取库存低于阈值的 SKU
    fun getLowStockSkus(threshold: Int): List<SkuEntity> = skuRepository.findByStockInfoStockQuantityLessThan(threshold)

    // 新增 SKU
    fun createSku(sku: SkuEntity): SkuEntity = skuRepository.save(sku)

    // 更新 SKU 信息（直接覆盖整个 SKU）
    fun updateSku(skuId: String, sku: SkuEntity): SkuEntity {
        val existingSku = skuRepository.findById(skuId).orElseThrow { RuntimeException("Sku not found") }
        return skuRepository.save(sku.copy(skuId = existingSku.skuId))
    }

    // 更新库存（扣减或补货）
    fun updateStock(skuId: String, newStockQuantity: Int): SkuEntity {
        val sku = skuRepository.findById(skuId).orElseThrow { RuntimeException("Sku not found") }
        val updatedStock = sku.stockInfo.copy(stockQuantity = newStockQuantity)
        val updatedSku = sku.copy(stockInfo = updatedStock)
        return skuRepository.save(updatedSku)
    }

    // 删除 SKU
    fun deleteSku(skuId: String) = skuRepository.deleteById(skuId)
}
