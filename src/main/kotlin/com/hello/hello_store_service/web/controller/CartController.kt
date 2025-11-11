package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.application.CartService
import com.hello.hello_store_service.data.model.entity.CartItem
import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.clean.CartViewClean
import com.hello.hello_store_service.web.model.view.CartView
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService,
    private val cartViewClean: CartViewClean
) {

    @GetMapping
    fun getCartView(): CartView? {
        val username = SecurityUtils.currentUsername()
        return cartViewClean.getCartView(username)
    }

    @PutMapping("/select/{skuId}")
    fun selectCartItem(@PathVariable skuId: String) {
        val username = SecurityUtils.currentUsername()
        cartService.selectCartItem(username, skuId)
    }

    @PutMapping("/select/all")
    fun selectAllItems(
        @RequestParam isSelected: Boolean
    ) {
        val username = SecurityUtils.currentUsername()
        cartService.selectSwitch(username, isSelected)
    }


    @DeleteMapping("/clear")
    fun clearCart() {
        val username = SecurityUtils.currentUsername()
        cartService.clearCart(username)
    }

    @PostMapping("/add")
    fun addCartItem(@RequestBody newItem: CartItem) {
        val username = SecurityUtils.currentUsername()
        cartService.addItem(username, newItem)
    }

    @DeleteMapping("/remove/{skuId}")
    fun removeItem(@PathVariable skuId: String) {
        val username = SecurityUtils.currentUsername()
        cartService.removeItem(username, skuId)
    }

    @PutMapping("/update/{skuId}")
    fun updateItemQuantity(
        @PathVariable skuId: String,
        @RequestParam count: Int
    ) {
        val username = SecurityUtils.currentUsername()
        cartService.updateItemQuantity(username, skuId, count)
    }


}
