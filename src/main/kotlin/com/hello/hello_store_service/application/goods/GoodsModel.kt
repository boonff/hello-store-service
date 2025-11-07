package com.hello.hello_store_service.application.goods

import com.hello.hello_store_service.data.model.entity.goods.SpecEntity
import com.hello.hello_store_service.data.model.entity.goods.SpecValue
import com.hello.hello_store_service.data.model.entity.goods.StockInfo
import com.hello.hello_store_service.data.model.entity.goods.Weight

class SkuModel(
    val skuId: String,                       // SKU ID
    val spuId: String,                       // 所属商品 ID
    val storeId: String,
    val specList: List<SpecDetail>,             // SKU 的规格组合
    val stockInfo: StockInfo,                // 库存信息
    val skuImage: String? = null,            // SKU 图
    val weight: Weight? = null,              // 重量
    val volume: Long? = null,                // 体积
    val profitPrice: Int? = null,            // 利润价
    val salePrice: Int,                      // SKU 真实售价
    val linePrice: Int? = null               // 划线价（原价）
)

data class SpecDetail(
    val specId: String,
    val specTitle: String,       // 规格名称

    val specValueId: String,     // 已选择的规格值 ID（方便后端识别）
    val specValue: String        // 已选择的规格值（如 "红色"）
){
    companion object{
        fun from(specEntity: SpecEntity, specValue: SpecValue): SpecDetail{
            return SpecDetail(
                specId = specEntity.specId,
                specTitle = specEntity.title,
                specValueId = specValue.specValueId,
                specValue = specValue.specValue
            )
        }
    }
}