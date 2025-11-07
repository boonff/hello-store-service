package com.hello.hello_store_service.web.model.view

import com.hello.hello_store_service.data.model.entity.PromotionStatus
import com.hello.hello_store_service.data.model.entity.goods.SpuTag
import com.hello.hello_store_service.web.model.view.goods.SpecDetailView


data class CartView(
    val isAllSelected: Boolean = false,   // 是否全选
    val selectedGoodsCount: Int = 0,      // 已选择商品数量
    val totalAmount: Int = 0,            // 总金额（分）
    val totalDiscountAmount: Int = 0,    // 总优惠金额（分）
    val storeGoods: List<StoreGoodsView>
)

data class StoreGoodsView(
    val storeId: String,
    val storeName: String,
    val storeStatus: Int,
    val totalDiscountSalePrice: Int,
    val promotionGoodsList: List<PromotionGoodsView>
)

data class PromotionGoodsView(
    val title: String,
    val promotionCode: String,
    val promotionSubCode: String,
    val promotionStatus: Int,
    val type: PromotionStatus,
    val description: String?,
    val doorSillRemain: Int?,
    val isNeedAddOnShop: Boolean,
    val goods: List<CartGoodsView>
)

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

    val specInfo: List<SpecDetailView>,

    val available: Int?,                     // 是否可售
    val price: Int?,
    val originPrice: Int?,

    val stockQuantity: Int,                  // 库存
    val safeStockQuantity: Int,              // 安全库存
    val soldQuantity: Int                    // 已售
)