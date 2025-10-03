package com.hello.hello_store_service.repository.goods

import com.hello.hello_store_service.model.entity.goods.Spu
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SpuRepository : MongoRepository<Spu, String>, SpuRepositoryCustom {
    // 自定义查询方法（基于命名规则）
    fun findByTitleContaining(keyword: String): List<Spu>
    fun findBySpuId(spuId: String): Spu?
    fun findBySaasIdAndSpuId(saasId: String, spuId: String): Spu?
    fun findByCategoryIdsContaining(categoryId: String): List<Spu>
    fun findAllBySpuIdIn(spuIds: List<String>): List<Spu>
}

interface SpuRepositoryCustom {
    fun updateSpu(spuId: String, spu: Spu): Spu
    fun findByRange(pageIndex: Int, pageSize: Int): List<Spu>
}
