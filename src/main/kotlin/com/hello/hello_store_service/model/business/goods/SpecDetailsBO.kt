package com.hello.hello_store_service.model.business.goods

import com.hello.hello_store_service.model.entity.goods.SkuEntity
import com.hello.hello_store_service.model.entity.goods.SpecEntity
import com.hello.hello_store_service.model.view.goods.SpecDetail


class SpecDetailsBO(
    private val sku: SkuEntity,
    private val specMap: Map<String, SpecEntity>,
){
    fun fetchSpecDetail(): List<SpecDetail> =
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
