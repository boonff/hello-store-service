package com.hello.hello_store_service.repository.cart

import com.hello.hello_store_service.model.entity.cart.Cart
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : MongoRepository<Cart, String> {

    // 根据用户查询该用户所有购物车（一个用户可能有多个门店的购物车）
    fun findByUsername(username: String): List<Cart>

    // 可选：批量删除用户的购物车
    fun deleteByUsername(userId: String)
}