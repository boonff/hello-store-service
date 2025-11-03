package com.hello.hello_store_service.web.trans.business

import com.hello.hello_store_service.data.model.entity.CartItem
import com.hello.hello_store_service.data.model.entity.goods.SkuEntity
import com.hello.hello_store_service.data.model.entity.goods.SpecEntity
import com.hello.hello_store_service.data.model.entity.goods.SpuEntity
import com.hello.hello_store_service.web.trans.view.cart.CartGoodsView
import com.hello.hello_store_service.web.trans.view.goods.SpecDetail

data class CartItemBO(
    val cartItem: CartItem,   // Cart 中的单个条目
    val sku: SkuEntity,
    val spu: SpuEntity,
    val specs: List<SpecEntity>
) {
    fun isSelected(): Boolean = cartItem.isSelected
    fun quantity(): Int = cartItem.count
    fun price(): Int = sku.salePrice.toInt()
    fun originPrice(): Int? = sku.linePrice?.toInt() ?: spu.maxLinePrice
    fun stockQuantity(): Int = sku.stockInfo.stockQuantity
    fun safeStockQuantity(): Int = sku.stockInfo.safeStockQuantity
    fun soldQuantity(): Int = sku.stockInfo.soldQuantity

    fun toCartGoodsView(): CartGoodsView {
        val specMap = specs.associateBy { it.specId }
        val specInfo = sku.specList.groupBy { it.specId }.mapNotNull { (specId, specRefs) ->
            specMap[specId]?.let { spec ->
                specRefs.firstOrNull()?.let { ref ->
                    spec.values.find { it.specValueId == ref.specValueId }?.let { value ->
                        SpecDetail(
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