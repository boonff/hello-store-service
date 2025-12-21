package com.hello.hello_store_service.web.view

import com.hello.hello_store_service.application.sku.SpecDetail

data class SpecInfo(
    val specTitle: String,               // 规格名称（如颜色、尺寸）
    val specValue: String                // 规格值（如米色、S码）
) {
    companion object {
        fun from(specDetail: SpecDetail): SpecInfo {
            return SpecInfo(
                specTitle = specDetail.specTitle,
                specValue = specDetail.specValue
            )
        }
    }
}
