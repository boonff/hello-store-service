package com.hello.hello_store_service.service.chart

import com.hello.hello_store_service.model.business.cart.CartItemBO
import com.hello.hello_store_service.model.view.cart.CartView
import com.hello.hello_store_service.model.view.cart.PromotionGoods
import com.hello.hello_store_service.model.view.cart.StoreGoods
import com.hello.hello_store_service.model.entity.cart.CartEntity
import com.hello.hello_store_service.model.entity.cart.CartItem
import com.hello.hello_store_service.model.entity.goods.SpuEntity
import com.hello.hello_store_service.model.entity.goods.SkuEntity
import com.hello.hello_store_service.repository.cart.CartRepository
import com.hello.hello_store_service.service.activity.PromotionService
import com.hello.hello_store_service.service.goods.SpuService
import com.hello.hello_store_service.service.goods.SkuService
import com.hello.hello_store_service.service.goods.SpecService
import com.hello.hello_store_service.service.store.StoreService
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val goodsService: SpuService,
    private val skuService: SkuService,
    private val specService: SpecService,
    private val storeService: StoreService,
    private val promotionService: PromotionService
) {

    /** 获取用户所有购物车 */
    fun getUserCart(username: String): CartView? {
        val cart = cartRepository.findByUsername(username).firstOrNull() ?: return null

        val skuIds = cart.items.map { it.skuId }
        val skuMap: Map<String, SkuEntity> = skuService.getSkusBySpuIds(skuIds).associateBy { it.skuId }

        val spuIds = skuMap.values.map { it.spuId }.distinct()
        val spuMap: Map<String, SpuEntity> = goodsService.fetchBySpuIds(spuIds).associateBy { it.spuId }

        val storeIds = spuMap.values.map { it.storeId }.distinct()
        val storeMap = storeService.getStoreByIds(storeIds).associateBy { it.storeId }

        val promotionMap = promotionService.getPromotionsByStores(storeIds).groupBy { it.storeId }

        val storeGoodsList = storeIds.mapNotNull { storeId ->
            val store = storeMap[storeId] ?: return@mapNotNull null

            val storeItems = cart.items.filter { spuMap[it.spuId]?.storeId == storeId }

            val promotionGoodsList = promotionMap[storeId]?.map { promo ->
                val promoGoods = storeItems.mapNotNull { item ->
                    skuMap[item.skuId]?.let { sku ->
                        spuMap[sku.spuId]?.let { spu ->
                            val specs = specService.getSpecsBySpuId(spu.spuId)
                            CartItemBO(item, sku, spu, specs).toCartGoodsView()
                        }
                    }
                }
                PromotionGoods(
                    title = promo.title,
                    promotionCode = promo.rule.type.name,
                    promotionSubCode = "", // 可根据业务逻辑填充
                    promotionStatus = promo.status,
                    type = promo.rule.type,
                    description = promo.title,
                    doorSillRemain = promo.rule.minAmount ?: 0L,
                    isNeedAddOnShop = false, // 可根据业务逻辑填充
                    goods = promoGoods
                )
            } ?: emptyList()

            StoreGoods(
                storeId = storeId,
                storeName = store.storeName,
                storeStatus = store.storeStatus,
                totalDiscountSalePrice = storeItems.sumOf { (skuMap[it.skuId]?.minSalePrice ?: 0L).toLong() },
                promotionGoodsList = promotionGoodsList
            )
        }

        val result = CartView(
            isAllSelected = cart.isAllSelected,
            selectedGoodsCount = cart.items.size,
            totalAmount = storeGoodsList.sumOf { it.totalDiscountSalePrice },
            totalDiscountAmount = 0, // 可根据促销逻辑计算
            storeGoods = storeGoodsList
        )

        return result

    }


    /** 添加商品到购物车 */
    fun addCartItem(username: String, newItem: CartItem) {
        // 获取用户购物车
        val cart = cartRepository.findByUsername(username).firstOrNull()

        if (cart == null) {
            // 如果没有购物车，创建一个新的
            val newCart = CartEntity(username = username, items = listOf(newItem))
            cartRepository.save(newCart)
        } else {
            // 已有购物车，更新或新增 CartItem
            val updatedItems = cart.items.toMutableList()
            val existingItemIndex = updatedItems.indexOfFirst { it.skuId == newItem.skuId }
            if (existingItemIndex >= 0) {
                // SKU 已存在，累加数量
                val existingItem = updatedItems[existingItemIndex]
                updatedItems[existingItemIndex] = existingItem.copy(count = existingItem.count + newItem.count)
            } else {
                // 新增 SKU
                updatedItems.add(newItem)
            }
            cartRepository.save(cart.copy(items = updatedItems))
        }
    }

    /** 选择/取消选择单个商品 **/
    fun selectCartItem(username: String, skuId: String){
        val cart = cartRepository.findByUsername(username).firstOrNull() ?: return
        val updateItem = cart.items.map{
            if (it.skuId == skuId) it.copy(isSelected = !it.isSelected) else it
        }
        cartRepository.save(cart.copy(items = updateItem))
    }
    /** 全选/取消商店 **/
    fun selectStoreCartItem(username: String, storeId:String, isSelected: Boolean){
        val cart = cartRepository.findByUsername(username).firstOrNull() ?: return
        val updateItem = cart.items.map{
            if (it.storeId == storeId) it.copy(isSelected = isSelected) else it
        }
        cartRepository.save(cart.copy(items = updateItem))
    }
    /** 全选/取消全选购物车 **/
    fun selectAllCartItems(username: String, isAllSelected:Boolean){
        val cart = cartRepository.findByUsername(username).firstOrNull() ?: return

        cartRepository.save(cart.copy(isAllSelected = isAllSelected))
    }

    /** 更新购物车中某个商品数量 */
    fun updateCartItem(username: String, skuId: String, count: Int) {
        val cart = cartRepository.findByUsername(username).firstOrNull() ?: return
        val updatedItems = cart.items.map {
            if (it.skuId == skuId) it.copy(count = count) else it
        }
        cartRepository.save(cart.copy(items = updatedItems))
    }

    /** 删除购物车中的商品 */
    fun removeCartItem(username: String, skuId: String) {
        val cart = cartRepository.findByUsername(username).firstOrNull() ?: return
        val updatedItems = cart.items.filter { it.skuId != skuId }
        cartRepository.save(cart.copy(items = updatedItems))
    }

    /** 清空购物车 */
    fun clearCart(username: String) {
        val cart = cartRepository.findByUsername(username).firstOrNull() ?: return
        cartRepository.save(cart.copy(items = emptyList()))
    }


}