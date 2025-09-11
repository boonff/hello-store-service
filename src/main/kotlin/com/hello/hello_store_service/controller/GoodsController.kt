package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.Goods
import com.hello.hello_store_service.service.GoodsService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/goods")
class GoodsController(
    private val goodsService: GoodsService
) {

    // 查询所有商品
    @GetMapping
    fun getAllGoods(): List<Goods> {
        return goodsService.findAllGoods()
    }

    // 根据关键字搜索商品
    @GetMapping("/search")
    fun searchGoods(@RequestParam keyword: String): List<Goods> {
        return goodsService.searchGoods(keyword)
    }

    // 根据 spuId 获取单个商品
    @GetMapping("/{spuId}")
    fun getGoodsBySpuId(@PathVariable spuId: String): Goods? {
        return goodsService.findBySpuId(spuId)
    }

    // 新增商品
    @PostMapping
    fun createGoods(@RequestBody goods: Goods): Goods {
        return goodsService.createGoods(goods)
    }

    // 更新商品
    @PutMapping("/{spuId}")
    fun updateGoods(@PathVariable spuId: String, @RequestBody goods: Goods): Goods {
        return goodsService.updateGoods(spuId, goods)
    }

    // 删除商品
    @DeleteMapping("/{spuId}")
    fun deleteGoods(@PathVariable spuId: String) {
        goodsService.deleteGoods(spuId)
    }
}
