package com.hello.hello_store_service.web.trans.business.settle

import com.hello.hello_store_service.data.model.entity.goods.SkuEntity

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