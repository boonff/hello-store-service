package com.hello.hello_store_service.model.dto.goods

import com.hello.hello_store_service.model.entity.goods.SpecValue

/**
 * 规格 DTO（为了前端好展示）
 */
data class SpecDTO(
    val specTitle: String,       // 规格名称
    val specValue: String,       // 已选择的规格值（如 "红色"）
    val specValueId: String      // 已选择的规格值 ID（方便后端识别）
)
