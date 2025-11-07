package com.hello.hello_store_service.data.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("carts")
data class CartEntity(
    @Id
    val id: String? = null,
    @Indexed(unique = true)
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
)
