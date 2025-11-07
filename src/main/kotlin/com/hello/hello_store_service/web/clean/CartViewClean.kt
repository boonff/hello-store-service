package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.CartService
import com.hello.hello_store_service.application.goods.SkuModel
import com.hello.hello_store_service.application.goods.SkuService
import com.hello.hello_store_service.application.goods.SpecDetail
import com.hello.hello_store_service.data.model.entity.CartEntity
import com.hello.hello_store_service.data.model.entity.CartItem
import com.hello.hello_store_service.data.model.entity.StoreEntity
import com.hello.hello_store_service.data.model.entity.goods.SpuEntity
import com.hello.hello_store_service.data.service.PromotionDataService
import com.hello.hello_store_service.data.service.StoreDataService
import com.hello.hello_store_service.data.service.goods.SpuDataService
import com.hello.hello_store_service.web.model.view.CartView
import com.hello.hello_store_service.web.model.view.CartGoodsView
import com.hello.hello_store_service.web.model.view.PromotionGoodsView
import com.hello.hello_store_service.web.model.view.StoreGoodsView
import com.hello.hello_store_service.web.model.view.goods.SpecDetailView
import org.springframework.stereotype.Service

@Service
class CartViewClean(
    private val storeDataService: StoreDataService,
    private val promotionDataService: PromotionDataService,
    private val spuDataService: SpuDataService,
    private val cartService: CartService,
    private val skuService: SkuService
) {
    fun getCartView(username: String): CartView? {
        val cartEntity = fetchCartEntity(username) ?: return null
        return CartView(
            isAllSelected = cartEntity.isAllSelected,
            selectedGoodsCount = getSelectedGoodsCount(cartEntity),
            totalAmount = totalFee(cartEntity),
            totalDiscountAmount = 0,
            storeGoods = getStoreGoodsList(cartEntity)
        )
    }

    private fun getStoreGoodsList(cartEntity: CartEntity): List<StoreGoodsView> {
        val cartGroup = cartEntity.items.groupBy { cartItem ->
            cartItem.storeId
        }
        return cartGroup.mapNotNull { (storeId, items) ->
            val storeEntity = fetchStoreEntity(storeId) ?: return@mapNotNull null
            StoreGoodsView(
                storeId = storeId,
                storeName = storeEntity.storeName,
                storeStatus = storeEntity.storeStatus,
                totalDiscountSalePrice = 0,
                promotionGoodsList = listOf(getPromotionGoodsList(storeId, items))
            )
        }

    }

    private fun getPromotionGoodsList(storeId: String, cartItem: List<CartItem>): PromotionGoodsView {
        val promotionEntity = promotionDataService.fetchByStore(storeId)
        return PromotionGoodsView(
            title = promotionEntity.title,
            promotionCode = promotionEntity.rule.type.name,
            promotionSubCode = "", //TODO promotionSubCode
            promotionStatus = promotionEntity.status,
            type = promotionEntity.rule.type,
            description = promotionEntity.description,
            doorSillRemain = promotionEntity.rule.minAmount,
            isNeedAddOnShop = false,
            goods = getGoodsList(cartItem)
        )
    }


    private fun getGoodsList(cartItem: List<CartItem>): List<CartGoodsView> {
        return cartItem.mapNotNull { cartItem ->
            val skuModel = fetchSkuModel(cartItem.skuId) ?: return@mapNotNull null
            val fetchSpuEntity = fetchSpuEntity(skuModel.spuId) ?: return@mapNotNull null
            CartGoodsView(
                spuId = skuModel.spuId,
                skuId = skuModel.skuId,
                saasId = "",
                storeId = skuModel.storeId,
                isSelected = cartItem.isSelected,
                quantity = cartItem.count,
                title = fetchSpuEntity.title,
                etitle = fetchSpuEntity.etitle,
                thumb = fetchSpuEntity.primaryImage,
                images = fetchSpuEntity.images,
                video = fetchSpuEntity.video,
                categoryIds = fetchSpuEntity.categoryIds,
                groupIdList = fetchSpuEntity.groupIdList,
                spuTagList = fetchSpuEntity.spuTagList,
                specInfo = getSpecDetailView(skuModel.specList),
                available = fetchSpuEntity.available,
                price = skuModel.salePrice,
                originPrice = skuModel.linePrice,
                stockQuantity = skuModel.stockInfo.stockQuantity,
                safeStockQuantity = skuModel.stockInfo.safeStockQuantity,
                soldQuantity = skuModel.stockInfo.soldQuantity
            )
        }

    }

    private fun totalFee(cartEntity: CartEntity): Int {
        return if (cartEntity.isAllSelected)
            cartEntity.items.sumOf { cartItem ->
                val skuModel = fetchSkuModel(cartItem.skuId) ?: return@sumOf 0
                skuModel.salePrice
            }
        else cartEntity.items.sumOf { cartItem ->
            val skuEntity = fetchSkuModel(cartItem.skuId) ?: return@sumOf 0
            if (cartItem.isSelected)
                skuEntity.salePrice
            else 0
        }
    }

    private fun getSelectedGoodsCount(cartEntity: CartEntity): Int {
        return if (cartEntity.isAllSelected)
            cartEntity.items.size
        else cartEntity.items.count { it.isSelected }
    }

    private fun fetchCartEntity(username: String): CartEntity? {
        return cartService.fetchCartEntity(username)
    }

    private fun fetchStoreEntity(storeId: String): StoreEntity? {
        return storeDataService.fetchStoreById(storeId)
    }

    private fun fetchSkuModel(skuId: String): SkuModel? {
        return skuService.fetchSkuModel(skuId)
    }

    private fun fetchSpuEntity(spuId: String): SpuEntity? {
        return spuDataService.fetchById(spuId)
    }

    private fun getSpecDetailView(specDetails: List<SpecDetail>): List<SpecDetailView> {
        return specDetails.map { specDetail ->
            SpecDetailView.from(specDetail)
        }
    }
}