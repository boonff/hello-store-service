package com.hello.hello_store_service.model.business.settle

import com.hello.hello_store_service.model.entity.goods.SkuEntity
import java.math.BigDecimal

data class SkuQuantityBO(
    val skuEntity: SkuEntity,
    val quantity: Int,
) {
    val totalPrice: Int
        get() = skuEntity.salePrice * quantity
}

fun SkuEntity.toSkuQuantityBO(quantity: Int): SkuQuantityBO =
    SkuQuantityBO(
        skuEntity = this,
        quantity
    )