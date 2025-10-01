package com.hello.hello_store_service.repository.goods

import com.hello.hello_store_service.model.entity.goods.Spec
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SpecRepository : MongoRepository<Spec, String> {

    // 根据 spuId 查找该商品的所有规格
    fun findBySpuId(spuId: String): List<Spec>

    // 根据规格名模糊查找（可选）
    fun findByTitleContaining(keyword: String): List<Spec>
}
