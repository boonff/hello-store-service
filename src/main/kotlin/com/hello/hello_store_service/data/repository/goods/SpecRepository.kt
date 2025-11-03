package com.hello.hello_store_service.data.repository.goods

import com.hello.hello_store_service.data.model.entity.goods.SpecEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SpecRepository : MongoRepository<SpecEntity, String> {

    // 根据 spuId 查找该商品的所有规格
    fun findBySpuId(spuId: String): List<SpecEntity>

    // 根据规格名模糊查找（可选）
    fun findByTitleContaining(keyword: String): List<SpecEntity>
}
