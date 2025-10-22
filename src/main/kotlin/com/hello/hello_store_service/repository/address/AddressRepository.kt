package com.hello.hello_store_service.repository.address

import com.hello.hello_store_service.model.entity.address.AddressEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : MongoRepository<AddressEntity, String> {
    fun findAllByUsername(username: String): List<AddressEntity> // 查询某个用户的所有地址
    fun findByUsernameAndIsDefaultTrue(username: String): AddressEntity? // 查询用户的默认地址
    fun findByUsernameAndIsOrderSureTrue(username: String): List<AddressEntity>  // 查询确认订单使用的地址
}
