package com.hello.hello_store_service.model.dto.cart

import com.hello.hello_store_service.model.entity.goods.Goods
import com.hello.hello_store_service.model.entity.activity.Promotion

data class CartDTO(
    val isAllSelected: Boolean = false,   // 是否全选
    val selectedGoodsCount: Int = 0,      // 已选择商品数量
    val totalAmount: Long = 0,            // 总金额（分）
    val totalDiscountAmount: Long = 0,    // 总优惠金额（分）
    val storeGoods: List<StoreGoods>
)

data class StoreGoods(
    val storeId: String,
    val storeName: String,
    val storeStatus: Int,
    val totalDiscountSalePrice: Long,
    val promotionGoodsList: List<PromotionGoods>
)

data class PromotionGoods(
    val promotionId: String,
    val title: String,
    val promotionCode: String,
    val promotionSubCode: String,
    val promotionStatus: Int,
    val tagText: List<String>,
    val tag: String,
    val description: String,
    val doorSillRemain: Long,
    val isNeedAddOnShop: Boolean,
    val goods: List<Goods>
)