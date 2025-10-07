package com.hello.hello_store_service.model.entity.cart

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("carts")
data class Cart(
    @Id
    val id: String? = null,

    val username: String,                   // 哪个用户的购物车
    val isAllSelected: Boolean = false,
    val items: List<CartItem>,        // 购物车中的商品
)


data class CartItem(
    val storeId: String,
    val spuId: String,           // 商品 SPU ID
    val skuId: String,           // 商品 SKU ID（对应具体规格）
    var isSelected: Boolean = true,
    val count: Int,              // 购买数量
    val price: Int? = null,      // 单价（缓存到购物车）
    val title: String? = null,   // 商品标题，方便前端显示
    val primaryImage: String? = null // 商品主图
)
