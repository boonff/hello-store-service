package com.hello.hello_store_service.model.dto.goods

import com.hello.hello_store_service.model.entity.goods.SpuTag

data class GoodsDetail(
    val spuId: String,                       // 商品 ID
    val skuId:String,
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
    val maxLinePrice: Int?,                  // 划线价

    val stockQuantity: Int,                  // 库存
    val safeStockQuantity: Int,              // 安全库存
    val soldQuantity: Int                    // 已售
)