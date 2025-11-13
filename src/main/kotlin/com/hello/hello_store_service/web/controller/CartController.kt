package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.application.CartService
import com.hello.hello_store_service.data.entity.CartItem
import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.clean.CartViewClean
import com.hello.hello_store_service.web.view.CartView
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService,
    private val cartViewClean: CartViewClean
) {

    @GetMapping
    fun getCartView(): CartView? {
        val uid = SecurityUtils.fetchUid()
        return cartViewClean.getCartView(uid)
    }

    @PutMapping("/select/{skuId}")
    fun selectCartItem(@PathVariable skuId: String) {
        val uid = SecurityUtils.fetchUid()
        cartService.selectCartItem(uid, skuId)
    }

    @PutMapping("/select/all")
    fun selectAllItems(
        @RequestParam isSelected: Boolean
    ) {
        val uid = SecurityUtils.fetchUid()
        cartService.selectSwitch(uid, isSelected)
    }


    @DeleteMapping("/clear")
    fun clearCart() {
        val uid = SecurityUtils.fetchUid()
        cartService.clearCart(uid)
    }

    @PostMapping("/add")
    fun addCartItem(@RequestBody newItem: CartItem) {
        val uid = SecurityUtils.fetchUid()
        cartService.addItem(uid, newItem)
    }

    @DeleteMapping("/remove/{skuId}")
    fun removeItem(@PathVariable skuId: String) {
        val uid = SecurityUtils.fetchUid()
        cartService.removeItem(uid, skuId)
    }

    @PutMapping("/update/{skuId}")
    fun updateItemQuantity(
        @PathVariable skuId: String,
        @RequestParam count: Int
    ) {
        val uid = SecurityUtils.fetchUid()
        cartService.updateItemQuantity(uid, skuId, count)
    }


}
