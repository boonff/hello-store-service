package com.hello.hello_store_service.service

import com.hello.hello_store_service.model.Goods

interface GoodsService {
    fun searchGoods(keyword: String): List<Goods>
    fun findAllGoods(): List<Goods>
    fun findByRange(pageIndex:Int, pageSize:Int):List<Goods>
    fun findBySpuId(supId: String): Goods?
    fun createGoods(goods: Goods): Goods
    fun updateGoods(spuId: String, goods: Goods): Goods
    fun deleteGoods(spuId: String)
}

