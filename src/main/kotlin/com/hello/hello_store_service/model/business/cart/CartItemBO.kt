package com.hello.hello_store_service.model.business.cart

import com.hello.hello_store_service.model.entity.cart.CartItem
import com.hello.hello_store_service.model.entity.goods.Sku
import com.hello.hello_store_service.model.entity.goods.Spec
import com.hello.hello_store_service.model.entity.goods.Spu
import com.hello.hello_store_service.model.view.goods.SelectedSpec
import com.hello.hello_store_service.model.view.cart.CartGoodsView

data class CartItemBO(
    val cartItem: CartItem,   // Cart 中的单个条目
    val sku: Sku,
    val spu: Spu,
    val specs: List<Spec>
) {
    fun isSelected(): Boolean = cartItem.isSelected
    fun quantity(): Int = cartItem.count
    fun price(): Int? = sku.minSalePrice ?: spu.minSalePrice
    fun originPrice(): Int? = sku.maxLinePrice ?: spu.maxLinePrice
    fun stockQuantity(): Int = sku.stockInfo.stockQuantity
    fun safeStockQuantity(): Int = sku.stockInfo.safeStockQuantity
    fun soldQuantity(): Int = sku.stockInfo.soldQuantity

    fun toCartGoodsView(): CartGoodsView {
        val specMap = specs.associateBy { it.specId }
        val specInfo = sku.specInfo.groupBy { it.specId }.mapNotNull { (specId, specRefs) ->
            specMap[specId]?.let { spec ->
                specRefs.firstOrNull()?.let { ref ->
                    spec.values.find { it.specValueId == ref.specValueId }?.let { value ->
                        SelectedSpec(
                            specId = spec.specId,
                            specTitle = spec.title,
                            specValue = value.specValue,
                            specValueId = value.specValueId
                        )
                    }
                }
            }
        }

        return CartGoodsView(
            spuId = spu.spuId,
            skuId = sku.skuId,
            saasId = spu.saasId,
            storeId = spu.storeId,
            isSelected = isSelected(),
            quantity = quantity(),
            title = spu.title,
            etitle = spu.etitle,
            thumb = spu.primaryImage,
            images = spu.images,
            video = spu.video,
            categoryIds = spu.categoryIds,
            groupIdList = spu.groupIdList,
            spuTagList = spu.spuTagList,
            specInfo = specInfo,
            available = spu.available,
            price = price(),
            originPrice = originPrice(),
            stockQuantity = stockQuantity(),
            safeStockQuantity = safeStockQuantity(),
            soldQuantity = soldQuantity()
        )
    }
}
