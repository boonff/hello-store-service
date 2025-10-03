package com.hello.hello_store_service.model.dto.goods

import com.hello.hello_store_service.model.entity.goods.*

/**
 * 商品详情 DTO（以 SPU 为主体，包含 SKU 和规格信息）
 */
data class Goods(
    val spuId: String,                       // 商品 ID
    val saasId: String,
    val storeId: String,

    val title: String,
    val etitle: String,
    val primaryImage: String,
    val images: List<String>,
    val video: String? = null,

    val categoryIds: List<String>?,
    val groupIdList: List<String>?,
    val spuTagList: List<SpuTag>,

    val desc: List<String>,                  // 图文详情

    val available: Int?,                     // 是否可售
    val minSalePrice: Int?,                  // 最低价
    val maxSalePrice: Int?,                  // 最高价
    val maxLinePrice: Int?,                  // 划线价

    val skus: List<SkuDTO>                   // 所有 SKU 信息
)


