package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.CartService
import com.hello.hello_store_service.application.sku.SkuModel
import com.hello.hello_store_service.application.sku.SkuService
import com.hello.hello_store_service.data.entity.CartEntity
import com.hello.hello_store_service.data.service.goods.SpuDataService
import com.hello.hello_store_service.web.view.CartGoodsView
import com.hello.hello_store_service.web.view.CartView
import org.springframework.stereotype.Service

@Service
class CartViewClean(
    private val spuDataService: SpuDataService,
    private val cartService: CartService,
    private val skuService: SkuService
) {
    fun fetchCartView(uid: String): CartView? {
        val cartEntity = fetchCartEntity(uid) ?: return null
        return CartView(
            isAllSelected = isAllSelected(cartEntity),
            selectedGoodsCount = getSelectedGoodsCount(cartEntity),
            totalAmount = totalFee(cartEntity),
            totalDiscountAmount = 0,
            goodsList = fetchCartGoodsView(cartEntity)
        )
    }

    private fun fetchCartGoodsView(cartEntity: CartEntity): List<CartGoodsView> {
        return cartEntity.items.mapNotNull { item ->
            val spuEntity = spuDataService.fetchById(item.spuId) ?: return@mapNotNull null
            val skuModel = skuService.fetchSkuModel(item.skuId) ?: return@mapNotNull null
            CartGoodsView(
                spuId = item.spuId,
                skuId = item.skuId,
                isSelected = item.isSelected,
                quantity = item.quantity,
                title = spuEntity.title,
                etitle = spuEntity.etitle,
                thumb = spuEntity.primaryImage,
                images = spuEntity.images,
                video = spuEntity.video,
                categoryIds = spuEntity.categoryIds,
                groupIdList = spuEntity.groupIdList,
                spuTagList = spuEntity.spuTagList,
                specInfo = skuModel.specList,
                available = spuEntity.available,
                price = skuModel.salePrice,
                originPrice = skuModel.linePrice,
                stockQuantity = skuModel.stockInfo.stockQuantity,
                safeStockQuantity = skuModel.stockInfo.safeStockQuantity,
                soldQuantity = skuModel.stockInfo.soldQuantity
            )
        }

    }

    private fun isAllSelected(cartEntity: CartEntity): Boolean =
        cartEntity.items.all { it.isSelected }

    private fun totalFee(cartEntity: CartEntity): Int {
        return cartEntity.items.sumOf { cartItem ->
            val skuEntity = fetchSkuModel(cartItem.skuId) ?: return@sumOf 0
            if (cartItem.isSelected)
                skuEntity.salePrice
            else 0
        }
    }

    private fun getSelectedGoodsCount(cartEntity: CartEntity): Int {
        return cartEntity.items.count { it.isSelected }
    }

    private fun fetchCartEntity(uid: String): CartEntity? {
        return cartService.fetchCartEntity(uid)
    }

    private fun fetchSkuModel(skuId: String): SkuModel? {
        return skuService.fetchSkuModel(skuId)
    }
}