package com.hello.hello_store_service.application

import com.hello.hello_store_service.data.model.entity.CartEntity
import com.hello.hello_store_service.data.model.entity.CartItem
import com.hello.hello_store_service.data.service.CartDataService
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartDataService: CartDataService
) {
    fun fetchCartEntity(username: String): CartEntity? {
        return cartDataService.fetchByUsername(username)
    }

    /** 全选/取消商店 **/
    fun selectStoreCartItem(username: String, storeId: String, isSelected: Boolean) {
        val cart = fetchCartEntity(username) ?: return
        val updateItem = cart.items.map {
            if (it.storeId == storeId) it.copy(isSelected = isSelected) else it
        }
        cartDataService.save(cart.copy(items = updateItem))
    }

    /** 全选/取消全选购物车 **/
    fun selectAllCartItems(username: String, isAllSelected: Boolean) {
        val cart = fetchCartEntity(username) ?: return

        cartDataService.save(cart.copy(isAllSelected = isAllSelected))
    }

    /** 更新购物车中某个商品数量 */
    fun updateCartItem(username: String, skuId: String, count: Int) {
        val cart = fetchCartEntity(username) ?: return
        val updatedItems = cart.items.map {
            if (it.skuId == skuId) it.copy(count = count) else it
        }
        cartDataService.save(cart.copy(items = updatedItems))
    }

    /** 删除购物车中的商品 */
    fun removeCartItem(username: String, skuId: String) {
        val cart = fetchCartEntity(username) ?: return
        val updatedItems = cart.items.filter { it.skuId != skuId }
        cartDataService.save(cart.copy(items = updatedItems))
    }

    /** 清空购物车 */
    fun clearCart(username: String) {
        val cart = fetchCartEntity(username) ?: return
        cartDataService.save(cart.copy(items = emptyList()))
    }

    fun addCartItem(username: String, newItem: CartItem) {
        val cart = fetchCartEntity(username)
        if (cart == null) {
            // 如果没有购物车，创建一个新的
            val newCart = CartEntity(username = username, items = listOf(newItem))
            cartDataService.save(newCart)
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
            cartDataService.save(cart.copy(items = updatedItems))
        }
    }

    fun selectCartItem(username: String, skuId: String) {
        val cart = fetchCartEntity(username) ?: return
        val updateItem = cart.items.map {
            if (it.skuId == skuId) it.copy(isSelected = !it.isSelected) else it
        }
        cartDataService.save(cart.copy(items = updateItem))
    }
}