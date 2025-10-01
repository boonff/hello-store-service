package com.hello.hello_store_service.service

import com.hello.hello_store_service.model.entity.goods.Sku
import com.hello.hello_store_service.repository.goods.SkuRepository
import org.springframework.stereotype.Service

@Service
class SkuService(
    private val skuRepository: SkuRepository
) {

    // 根据 spuId 获取商品下的所有 SKU
    fun getSkusBySpuId(spuId: String): List<Sku> =
        skuRepository.findBySpuId(spuId)

    // 批量获取多个商品的 SKU
    fun getSkusBySpuIds(spuIds: List<String>): List<Sku> =
        skuRepository.findAllBySpuIdIn(spuIds)

    // 获取库存低于阈值的 SKU
    fun getLowStockSkus(threshold: Int): List<Sku> =
        skuRepository.findByStockInfoStockQuantityLessThan(threshold)

    // 新增 SKU
    fun createSku(sku: Sku): Sku =
        skuRepository.save(sku)

    // 更新 SKU 信息（直接覆盖整个 SKU）
    fun updateSku(skuId: String, sku: Sku): Sku {
        val existingSku = skuRepository.findById(skuId).orElseThrow { RuntimeException("Sku not found") }
        return skuRepository.save(sku.copy(skuId = existingSku.skuId))
    }

    // 更新库存（扣减或补货）
    fun updateStock(skuId: String, newStockQuantity: Int): Sku {
        val sku = skuRepository.findById(skuId).orElseThrow { RuntimeException("Sku not found") }
        val updatedStock = sku.stockInfo.copy(stockQuantity = newStockQuantity)
        val updatedSku = sku.copy(stockInfo = updatedStock)
        return skuRepository.save(updatedSku)
    }

    // 删除 SKU
    fun deleteSku(skuId: String) =
        skuRepository.deleteById(skuId)
}
