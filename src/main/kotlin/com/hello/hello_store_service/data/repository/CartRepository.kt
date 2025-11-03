package com.hello.hello_store_service.data.repository

import com.hello.hello_store_service.data.model.entity.CartEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : MongoRepository<CartEntity, String> {

    // 根据用户查询该用户所有购物车（一个用户可能有多个门店的购物车）
    fun findByUsername(username: String): List<CartEntity>

    // 可选：批量删除用户的购物车
    fun deleteByUsername(userId: String)
}