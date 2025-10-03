package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.dto.cart.CartDTO
import com.hello.hello_store_service.model.entity.cart.CartItem
import com.hello.hello_store_service.service.chart.CartService
import com.hello.hello_store_service.util.SecurityUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService
) {

    /** 获取当前用户购物车 */
    @GetMapping
    fun getCart(): CartDTO? {
        val username = SecurityUtils.currentUsername()
        return cartService.getUserCart(username)
    }

    /** 选择/取消选择单个商品 **/
    @PutMapping("/select/{skuId}")
    fun selectCartItem(
        @PathVariable skuId: String,
        @RequestParam isSelected: Boolean
    ) {
        val username = SecurityUtils.currentUsername()
        cartService.selectCartItem(username, skuId, isSelected)
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
