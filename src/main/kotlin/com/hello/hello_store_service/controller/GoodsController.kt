package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.entity.goods.Goods
import com.hello.hello_store_service.model.entity.goods.SpuTag
import com.hello.hello_store_service.service.FileService
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
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/goods")
class GoodsController(
    private val goodsService: GoodsService,
    private val fileService: FileService,
) {
    // 查询所有商品
    @GetMapping
    fun getAllGoods(): List<Goods> {
        return goodsService.findAllGoods()
    }

    // 查询区间内的商品
    @GetMapping("/range")
    fun getRangeGoods(
        @RequestParam("pageIndex") pageIndex: Int,
        @RequestParam("pageSize") pageSize: Int
    ): List<Goods> {
        return goodsService.findByRange(pageIndex, pageSize)
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

    @PostMapping("/create")
    fun createGoods(
        @RequestParam title: String,
        @RequestParam etitle: String,
        @RequestParam primaryImage: MultipartFile,
        @RequestParam images: List<MultipartFile>,
        @RequestParam desc: List<MultipartFile>,
        @RequestParam(required = false) spuTagList: List<SpuTag> = emptyList(),
        @RequestParam(required = false) categoryIds: List<String> = emptyList(),
        @RequestParam(required = false) groupIdList: List<String> = emptyList(),
        @RequestParam(required = false, defaultValue = "1") isPutOnSale: Int
    ): Goods {
        return goodsService.createGoods(
            saasId = "88888888",
            storeId = "1000",
            title = title,
            etitle = etitle,
            primaryImage = primaryImage,
            images = images,
            desc = desc,
            spuTagList = spuTagList,
            categoryIds = categoryIds,
            groupIdList = groupIdList,
            isPutOnSale = isPutOnSale
        )
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
