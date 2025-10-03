package com.hello.hello_store_service.model.entity.goods

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("specs")
data class Spec(
    @Id
    val id: String? = null,
    val specId: String,                      // 规格 ID
    val spuId: String,                       // 所属商品 ID

    val title: String,                       // 规格名称（如 "颜色"）
    val values: List<SpecValue>              // 可选值
)

data class SpecValue(
    val specValueId: String,                 // 规格值 ID
    val specValue: String,                   // 值（如 "红色"）
    val image: String? = null                // 示例图
)
