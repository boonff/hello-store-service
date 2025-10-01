package com.hello.hello_store_service.service

import com.hello.hello_store_service.model.entity.Goods
import com.hello.hello_store_service.repository.GoodsRepository
import org.springframework.stereotype.Service

@Service
class GoodsService(
    private val goodsRepository: GoodsRepository
) {
    fun searchGoods(keyword: String): List<Goods> {
        return goodsRepository.findByTitleContaining(keyword)
    }
    fun fetchGoodsBySpuIds(spuIds: List<String>): List<Goods> {
        return goodsRepository.findAllBySpuIdIn(spuIds)
    }

    fun findAllGoods(): List<Goods> {
        return goodsRepository.findAll()
    }

    fun findByRange(pageIndex: Int, pageSize: Int): List<Goods> {
        return goodsRepository.findByRange(pageIndex, pageSize)
    }

    fun findBySpuId(supId: String): Goods? {
        return goodsRepository.findBySpuId(supId)
    }

    fun createGoods(goods: Goods): Goods {
        return goodsRepository.save(goods)
    }

     fun updateGoods(
        spuId: String,
        goods: Goods
    ): Goods {
        return goodsRepository.updateGoods(spuId, goods)
    }

     fun deleteGoods(spuId: String) {
        return goodsRepository.deleteById(spuId)
    }
}