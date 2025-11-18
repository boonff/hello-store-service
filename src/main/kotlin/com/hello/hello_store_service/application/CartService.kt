package com.hello.hello_store_service.application

import com.hello.hello_store_service.data.entity.CartEntity
import com.hello.hello_store_service.data.entity.CartItem
import com.hello.hello_store_service.data.service.CartDataService
import com.hello.hello_store_service.web.request.CartRequest
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartDataService: CartDataService
) {
    fun addItem(uid: String, request: CartRequest) {
        val cartEntity = fetchCartEntity(uid)

        val newCart = if (cartEntity == null) {
            // 用户没有购物车，直接创建
            genCartEntity(uid, request)
        } else {
            // 如果购物车里已有该商品，更新数量，否则添加新商品
            val exists = cartEntity.items.any { it.skuId == request.skuId }

            if (exists) updateQuantity(cartEntity, request)
            else cartEntity.copy(
                items = cartEntity.items + CartItem(
                    spuId = request.spuId,
                    skuId = request.skuId,
                    isSelected = true,
                    quantity = request.count
                )
            )
        }

        cartDataService.save(newCart)
    }

    private fun genCartEntity(uid: String, request: CartRequest): CartEntity {
        return CartEntity(
            uid = uid,
            items = listOf(
                CartItem(
                    spuId = request.spuId,
                    skuId = request.skuId,
                    isSelected = true,
                    quantity = request.count
                )
            )
        )
    }

    private fun updateQuantity(cartEntity: CartEntity, request: CartRequest): CartEntity {
        return cartEntity.copy(
            items = cartEntity.items.map { item ->
                if (item.skuId == request.skuId) item.copy(quantity = item.quantity + request.count)
                else item
            }
        )
    }

    fun selectCartItem(uid: String, skuId: String) {
        val cart = fetchCartEntity(uid) ?: return
        val updateItem = cart.items.map {
            if (it.skuId == skuId) it.copy(isSelected = !it.isSelected) else it
        }
        cartDataService.save(cart.copy(items = updateItem))
    }

    fun selectSwitch(uid: String, boolean: Boolean) {
        val cart = fetchCartEntity(uid) ?: return

        val newCart = cart.copy(
            items = cart.items.map { item ->
                item.copy(isSelected = boolean)
            })
        cartDataService.save(newCart)
    }

    fun clearCart(uid: String) {
        val cart = fetchCartEntity(uid) ?: return
        cartDataService.save(cart.copy(items = emptyList()))
    }

    fun updateItemQuantity(uid: String, skuId: String, count: Int) {
        val cart = fetchCartEntity(uid) ?: return
        val updatedItems = cart.items.map {
            if (it.skuId == skuId) it.copy(quantity = count) else it
        }
        cartDataService.save(cart.copy(items = updatedItems))
    }

    fun removeItem(uid: String, skuId: String): CartEntity? {
        val cart = fetchCartEntity(uid) ?: return null
        val updatedItems = cart.items.filter { it.skuId != skuId }
        return cartDataService.save(cart.copy(items = updatedItems))
    }

    fun fetchCartEntity(uid: String): CartEntity? {
        return cartDataService.fetchByUid(uid)
    }
}