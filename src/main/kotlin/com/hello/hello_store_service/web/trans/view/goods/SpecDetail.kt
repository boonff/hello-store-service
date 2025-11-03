package com.hello.hello_store_service.web.trans.view.goods

import com.hello.hello_store_service.data.model.entity.goods.SkuEntity
import com.hello.hello_store_service.data.model.entity.goods.SpecEntity


data class SpecDetail(
    val specId: String,
    val specTitle: String,       // 规格名称

    val specValueId: String,     // 已选择的规格值 ID（方便后端识别）
    val specValue: String        // 已选择的规格值（如 "红色"）

){
    fun from(
        specMap: Map<String, SpecEntity>,
        sku: SkuEntity
    ): List<SpecDetail> =
        sku.specList.map { (specId, specValueId) ->
            SpecDetail(
                specId = specId,
                specValueId = specValueId,
                specTitle = specMap[specId]?.title ?: "null",
                specValue = specMap[specId]?.values
                    ?.firstOrNull { it.specValueId == specValueId }
                    ?.specValue ?: ""
            )

        }
}
