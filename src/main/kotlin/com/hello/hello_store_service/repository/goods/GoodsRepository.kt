package com.hello.hello_store_service.repository.goods

import com.hello.hello_store_service.model.entity.goods.Goods
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GoodsRepository : MongoRepository<Goods, String>, GoodsRepositoryCustom {
    // 自定义查询方法（基于命名规则）
    fun findByTitleContaining(keyword: String): List<Goods>
    fun findBySpuId(spuId: String): Goods?
    fun findBySaasIdAndSpuId(saasId: String, spuId: String): Goods?
    fun findByCategoryIdsContaining(categoryId: String): List<Goods>
    fun findAllBySpuIdIn(spuIds: List<String>): List<Goods>
}

interface GoodsRepositoryCustom {
    fun updateGoods(spuId: String, goods: Goods): Goods
    fun findByRange(pageIndex: Int, pageSize: Int): List<Goods>
}
