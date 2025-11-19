package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.application.SpuService
import com.hello.hello_store_service.application.history.HistorySearchService
import com.hello.hello_store_service.data.entity.goods.SpuEntity
import com.hello.hello_store_service.data.service.goods.SkuDataService
import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.clean.SpuViewClean
import com.hello.hello_store_service.web.view.goods.SkuDetail
import com.hello.hello_store_service.web.view.goods.SpuView
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/goods")
class GoodsController(
    private val spuService: SpuService,
    private val skuDataService: SkuDataService,
    private val spuViewClean: SpuViewClean,
    private val historySearchService: HistorySearchService
) {
    // 查询区间内的商品
    @GetMapping("/range")
    fun getRangeGoods(
        @RequestParam("pageIndex") pageIndex: Int,
        @RequestParam("pageSize") pageSize: Int
    ): List<SpuEntity> {
        return spuService.fetchByRange(pageIndex, pageSize)
    }

    // 根据关键字搜索商品
    @GetMapping("/search")
    fun searchGoods(@RequestParam keyword: String): List<SpuEntity> {
        val uid = SecurityUtils.fetchUid()
        return spuService.search(uid, keyword)
    }

    // 根据 spuId 获取单个商品
    @GetMapping("/{spuId}")
    fun fetchGoodsBySpuId(@PathVariable spuId: String): SpuView? {
        return spuViewClean.fetchSpuView(spuId)
    }

    // 根据supId获取sku
    @GetMapping("/{spuId}/skus")
    fun getSkusBySpuId(@PathVariable spuId: String): List<SkuDetail> {
        return skuDataService.fetchDetails(spuId)
    }

    @GetMapping("search_history")
    fun fetchSearchHistory(): List<String> {
        val uid = SecurityUtils.fetchUid()
        return historySearchService.fetchHistorySearch(uid)
    }
}
