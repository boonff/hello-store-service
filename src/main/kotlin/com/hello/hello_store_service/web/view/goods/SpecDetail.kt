package com.hello.hello_store_service.web.view.goods

import com.hello.hello_store_service.application.goods.SpecDetail


data class SpecDetailView(
    val specId: String,
    val specTitle: String,       // 规格名称

    val specValueId: String,     // 已选择的规格值 ID（方便后端识别）
    val specValue: String        // 已选择的规格值（如 "红色"）
) {
    companion object {
        fun from(specDetail: SpecDetail): SpecDetailView {
            return SpecDetailView(
                specId = specDetail.specId,
                specTitle = specDetail.specTitle,
                specValueId = specDetail.specValueId,
                specValue = specDetail.specValue
            )
        }
    }
}