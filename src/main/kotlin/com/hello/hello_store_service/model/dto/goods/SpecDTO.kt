package com.hello.hello_store_service.model.dto.goods

import com.hello.hello_store_service.model.entity.goods.SpecValue

/**
 * 规格 DTO（为了前端好展示）
 */
data class SpecDTO(
    val specId: String,
    val title: String,
    val values: List<SpecValue>
)
