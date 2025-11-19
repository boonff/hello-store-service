package com.hello.hello_store_service.web.view

import com.hello.hello_store_service.application.sku.SpecDetail
import com.hello.hello_store_service.data.entity.goods.SpuTag



data class CartView(
    val isAllSelected: Boolean = false,   // 是否全选
    val selectedGoodsCount: Int = 0,      // 已选择商品数量
    val totalAmount: Int = 0,            // 总金额（分）
    val totalDiscountAmount: Int = 0,    // 总优惠金额（分）
    val goodsList: List<CartGoodsView>
)

data class CartGoodsView(
    val spuId: String,                       // 商品 ID
    val skuId: String,

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