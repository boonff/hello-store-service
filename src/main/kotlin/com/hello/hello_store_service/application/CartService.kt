package com.hello.hello_store_service.application

import com.hello.hello_store_service.data.model.entity.CartEntity
import com.hello.hello_store_service.data.model.entity.CartItem
import com.hello.hello_store_service.data.service.CartDataService
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartDataService: CartDataService
) {
    fun addItem(username: String, newItem: CartItem) {
        val cartEntity = fetchCartEntity(username)

        val newCart = if (cartEntity == null) {
            // 用户没有购物车，直接创建
            CartEntity(username = username, items = listOf(newItem))
        } else {
            // 如果购物车里已有该商品，更新数量，否则添加新商品
            val exists = cartEntity.items.any { it.skuId == newItem.skuId }

            if (exists) updateQuantity(cartEntity, newItem)
            else cartEntity.copy(items = cartEntity.items + newItem)
        }

        cartDataService.save(newCart)
    }

    private fun updateQuantity(cartEntity: CartEntity, newItem: CartItem): CartEntity {
        return cartEntity.copy(
            items = cartEntity.items.map { item ->
                if (item.skuId == newItem.skuId) item.copy(quantity = item.quantity + newItem.quantity)
                else item
            }
        )
    }

    fun selectCartItem(username: String, skuId: String) {
        val cart = fetchCartEntity(username) ?: return
        val updateItem = cart.items.map {
            if (it.skuId == skuId) it.copy(isSelected = !it.isSelected) else it
        }
        cartDataService.save(cart.copy(items = updateItem))
    }

    fun selectSwitch(username: String, boolean: Boolean) {
        val cart = fetchCartEntity(username) ?: return

        val newCart = cart.copy(
            items = cart.items.map { item ->
                item.copy(isSelected = boolean)
            })
        cartDataService.save(newCart)
    }

    fun clearCart(username: String) {
        val cart = fetchCartEntity(username) ?: return
        cartDataService.save(cart.copy(items = emptyList()))
    }

    fun updateItemQuantity(username: String, skuId: String, count: Int) {
        val cart = fetchCartEntity(username) ?: return
        val updatedItems = cart.items.map {
            if (it.skuId == skuId) it.copy(quantity = count) else it
        }
        cartDataService.save(cart.copy(items = updatedItems))
    }

    fun removeItem(username: String, skuId: String) {
        val cart = fetchCartEntity(username) ?: return
        val updatedItems = cart.items.filter { it.skuId != skuId }
        cartDataService.save(cart.copy(items = updatedItems))
    }

    fun fetchCartEntity(username: String): CartEntity? {
        return cartDataService.fetchByUsername(username)
    }
}