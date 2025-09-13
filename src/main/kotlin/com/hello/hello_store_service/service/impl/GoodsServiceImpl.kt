package com.hello.hello_store_service.service.impl

import com.hello.hello_store_service.model.Goods
import com.hello.hello_store_service.repository.GoodsRepository
import com.hello.hello_store_service.service.GoodsService
import org.springframework.stereotype.Service

@Service
class GoodsServiceImpl(
    private val goodsRepository: GoodsRepository
) : GoodsService {
    override fun searchGoods(keyword: String): List<Goods> {
        return goodsRepository.findByTitleContaining(keyword)
    }

    override fun findAllGoods(): List<Goods> {
        return goodsRepository.findAll()
    }

    override fun findByRange(pageIndex: Int, pageSize: Int): List<Goods> {
        return goodsRepository.findByRange(pageIndex, pageSize)
    }

    override fun findBySpuId(supId: String): Goods? {
        return goodsRepository.findBySpuId(supId)
    }

    override fun createGoods(goods: Goods): Goods {
        return goodsRepository.save(goods)
    }

    override fun updateGoods(
        spuId: String,
        goods: Goods
    ): Goods {
        return goodsRepository.updateGoods(spuId, goods)
    }

    override fun deleteGoods(spuId: String) {
        return goodsRepository.deleteById(spuId)
    }
}