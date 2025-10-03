package com.hello.hello_store_service.model.dto.cart

import com.hello.hello_store_service.model.dto.goods.SpecDTO
import com.hello.hello_store_service.model.entity.cart.Cart
import com.hello.hello_store_service.model.entity.goods.Sku
import com.hello.hello_store_service.model.entity.goods.Spec
import com.hello.hello_store_service.model.entity.goods.Spu
import com.hello.hello_store_service.model.entity.goods.SpuTag

data class CartGoodsDetail(
    val spuId: String,                       // 商品 ID
    val skuId: String,
    val saasId: String,
    val storeId: String,

    val quantity: Int,                       //选购数量
    val title: String,
    val etitle: String,
    val thumb: String,
    val images: List<String>,
    val video: String? = null,

    val categoryIds: List<String>?,
    val groupIdList: List<String>?,
    val spuTagList: List<SpuTag>,

    val specInfo: List<SpecDTO>,

    val available: Int?,                     // 是否可售
    val price: Int?,
    val originPrice: Int?,

    val stockQuantity: Int,                  // 库存
    val safeStockQuantity: Int,              // 安全库存
    val soldQuantity: Int                    // 已售
) {
    companion object {
        fun from(spu: Spu, sku: Sku, specMap: Map<String, Spec>, cart: Cart): CartGoodsDetail {
            val specInfo = sku.specInfo.groupBy { it.specId }.mapNotNull { (specId, specRefs) ->
                specMap[specId]?.let { spec ->
                    specRefs.firstOrNull()?.let { ref ->
                        spec.values.find { it.specValueId == ref.specValueId }?.let { value ->
                            SpecDTO(
                                specTitle = spec.title,
                                specValue = value.specValue,
                                specValueId = value.specValueId
                            )
                        }

                    }
                }
            }

            val quantity = cart.items.find { it.skuId == sku.skuId }?.count ?: 0

            return CartGoodsDetail(
                spuId = spu.spuId,
                skuId = sku.skuId,
                saasId = spu.saasId,
                storeId = spu.storeId,
                quantity = quantity,
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
                price = sku.minSalePrice ?: spu.minSalePrice,
                originPrice = sku.maxLinePrice ?: spu.maxLinePrice,
                stockQuantity = sku.stockInfo.stockQuantity,
                safeStockQuantity = sku.stockInfo.safeStockQuantity,
                soldQuantity = sku.stockInfo.soldQuantity
            )
        }
    }
}