package com.hello.hello_store_service.model.view.cart

import com.hello.hello_store_service.model.entity.goods.SpuTag
import com.hello.hello_store_service.model.view.goods.SpecDetail

data class CartGoodsView(
    val spuId: String,                       // 商品 ID
    val skuId: String,
    val saasId: String,
    val storeId: String,

    var isSelected: Boolean = true,          //是否选中
    val quantity: Int,                       //选购数量
    val title: String,
    val etitle: String,
    val thumb: String,
    val images: List<String>,
    val video: String? = null,

    val categoryIds: List<String>?,
    val groupIdList: List<String>?,
    val spuTagList: List<SpuTag>,

    val specInfo: List<SpecDetail>,

    val available: Int?,                     // 是否可售
    val price: Int?,
    val originPrice: Int?,

    val stockQuantity: Int,                  // 库存
    val safeStockQuantity: Int,              // 安全库存
    val soldQuantity: Int                    // 已售
)