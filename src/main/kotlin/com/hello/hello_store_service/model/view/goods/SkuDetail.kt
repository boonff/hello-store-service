package com.hello.hello_store_service.model.view.goods

import com.hello.hello_store_service.model.entity.goods.SkuEntity
import com.hello.hello_store_service.model.entity.goods.SpecRef
import com.hello.hello_store_service.model.entity.goods.StockInfo
import com.hello.hello_store_service.model.entity.goods.Weight
import org.springframework.data.annotation.Id

data class SkuDetail(
    val skuId: String,                       // SKU ID
    val spuId: String,                       // 所属商品 ID
    val storeId: String,
    val specInfo: List<SpecRef>,             // SKU 的规格组合
    val stockInfo: StockInfo,                // 库存信息
    val skuImage: String? = null,            // SKU 图
    val weight: Weight? = null,              // 重量
    val volume: Long? = null,                 // 体积
    val profitPrice: Int? = null,             // 利润价
    val minSalePrice: Int? = null,            // SKU 真实售价（可选缓存参考）
    val maxLinePrice: Int? = null             // 划线价（原价）
){
    companion object{
        fun from(skuEntity: SkuEntity) =
            SkuDetail(
                skuId = skuEntity.skuId,
                spuId = skuEntity.spuId,
                storeId = skuEntity.storeId,
                specInfo = skuEntity.specList,
                stockInfo = skuEntity.stockInfo,
                skuImage = skuEntity.skuImage,
                weight = skuEntity.weight,
                volume = skuEntity.volume,
                profitPrice = skuEntity.profitPrice?.toInt(),
                minSalePrice = skuEntity.salePrice.toInt(),
                maxLinePrice = skuEntity.linePrice?.toInt()
            )
    }
}