package com.hello.hello_store_service.data.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("carts")
data class CartEntity(
    @Id val cartId: String? = null,
    @Indexed(unique = true)
    val uid: String,                 // 哪个用户的购物车
    val items: List<CartItem>,       // 购物车中的商品
)

data class CartItem(
    val spuId: String,               // 商品 SPU ID
    val skuId: String,               // 商品 SKU ID（对应具体规格）
    var isSelected: Boolean = true,
    val quantity: Int,               // 购买数量
)
