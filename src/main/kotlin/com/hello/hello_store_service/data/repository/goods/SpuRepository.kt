package com.hello.hello_store_service.data.repository.goods

import com.hello.hello_store_service.data.entity.goods.SpuEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SpuRepository : MongoRepository<SpuEntity, String>, SpuRepositoryCustom {
    // 自定义查询方法（基于命名规则）
    fun findByTitleContaining(keyword: String): List<SpuEntity>
    fun findBySpuId(spuId: String): SpuEntity?
    fun findByCategoryIdsContaining(categoryId: String): List<SpuEntity>
    fun findAllBySpuIdIn(spuIds: List<String>): List<SpuEntity>
}

interface SpuRepositoryCustom {
    fun updateSpu(spuId: String, spu: SpuEntity): SpuEntity
    fun findByRange(pageIndex: Int, pageSize: Int): List<SpuEntity>
}
