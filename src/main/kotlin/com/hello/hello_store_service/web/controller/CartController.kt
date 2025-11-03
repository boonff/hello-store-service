package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.data.model.entity.CartItem
import com.hello.hello_store_service.data.service.CartService
import com.hello.hello_store_service.util.SecurityUtils
import com.hello.hello_store_service.web.trans.view.cart.CartView
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService
) {

    /** 获取当前用户购物车 */
    @GetMapping
    fun getCart(): CartView? {
        val username = SecurityUtils.currentUsername()
        return cartService.getUserCart(username)
    }

    /** 选择/取消选择单个商品 **/
    @PutMapping("/select/{skuId}")
    fun selectCartItem(
        @PathVariable skuId: String,
    ) {
        val username = SecurityUtils.currentUsername()
        cartService.selectCartItem(username, skuId)
    }
    /** 全选/取消商店 **/
    @PutMapping("/select/store/{storeId}")
    fun selectStoreCartItem(
        @PathVariable storeId: String,
        @RequestParam isSelected: Boolean
    ) {
        val username = SecurityUtils.currentUsername()
        cartService.selectStoreCartItem(username, storeId, isSelected)
    }
    /** 全选/取消全选购物车 **/
    @PutMapping("/select/all")
    fun selectAllCartItems(
        @RequestParam isSelected: Boolean
    ) {
        val username = SecurityUtils.currentUsername()
        cartService.selectAllCartItems(username, isSelected)
    }

    /** 添加商品到购物车 */
    @PostMapping("/add")
    fun addCartItem(@RequestBody newItem: CartItem) {
        val username = SecurityUtils.currentUsername()
        cartService.addCartItem(username, newItem)
    }

    /** 更新购物车中某个商品数量 */
    @PutMapping("/update/{skuId}")
    fun updateCartItem(
        @PathVariable skuId: String,
        @RequestParam count: Int
    ) {
        val username = SecurityUtils.currentUsername()
        cartService.updateCartItem(username, skuId, count)
    }

    /** 删除购物车中的商品 */
    @DeleteMapping("/remove/{skuId}")
    fun removeCartItem(@PathVariable skuId: String) {
        val username = SecurityUtils.currentUsername()
        cartService.removeCartItem(username, skuId)
    }

    /** 清空购物车 */
    @DeleteMapping("/clear")
    fun clearCart() {
        val username = SecurityUtils.currentUsername()
        cartService.clearCart(username)
    }
}
