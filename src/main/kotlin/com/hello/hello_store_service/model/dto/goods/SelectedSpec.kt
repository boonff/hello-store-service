package com.hello.hello_store_service.model.dto.goods

/**
 * 规格 DTO（为了前端好展示）
 */
data class SelectedSpec(
    val specId: String,
    val specTitle: String,       // 规格名称

    val specValueId: String,     // 已选择的规格值 ID（方便后端识别）
    val specValue: String        // 已选择的规格值（如 "红色"）

)
