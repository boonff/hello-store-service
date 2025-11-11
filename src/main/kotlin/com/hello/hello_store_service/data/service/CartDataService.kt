package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.model.entity.CartEntity
import com.hello.hello_store_service.data.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartDataService(
    private val cartRepository: CartRepository,
) {
    fun fetchByUsername(username: String): CartEntity? {
        return cartRepository.findByUsername(username)
    }

    fun save(cartEntity: CartEntity) {
        cartRepository.save(cartEntity)
    }

    fun create(cartEntity: CartEntity) {
        require(cartEntity.id == null) { "新增购物车时，id 必须为空" }
        cartRepository.save(cartEntity)
    }

    fun update(cartEntity: CartEntity) {
        requireNotNull(cartEntity.id) { "更新购物车时，id 不能为空" }
        require(cartRepository.existsById(cartEntity.id)) { "要更新的购物车不存在" }
        cartRepository.save(cartEntity)
    }
}