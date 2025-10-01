package com.hello.hello_store_service.service.chart

import com.hello.hello_store_service.model.entity.cart.Cart
import com.hello.hello_store_service.model.entity.cart.CartItem
import com.hello.hello_store_service.repository.cart.CartRepository
import com.hello.hello_store_service.repository.goods.GoodsRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val goodsRepository: GoodsRepository
) {

    /** 获取用户所有购物车 */
    fun getUserCart(username: String): List<Cart> =
        cartRepository.findByUsername(username)

    /** 添加商品到购物车 */
    fun addCartItem(username: String, newItem: CartItem) {
        // 获取用户购物车
        val cart = cartRepository.findByUsername(username).firstOrNull()

        if (cart == null) {
            // 如果没有购物车，创建一个新的
            val newCart = Cart(username = username, items = listOf(newItem))
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