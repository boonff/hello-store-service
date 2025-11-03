package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.data.model.entity.goods.SpuEntity
import com.hello.hello_store_service.data.model.entity.goods.SpuTag
import com.hello.hello_store_service.data.service.FileService
import com.hello.hello_store_service.data.service.goods.SkuService
import com.hello.hello_store_service.data.service.goods.SpuService
import com.hello.hello_store_service.web.trans.view.goods.SkuDetail
import com.hello.hello_store_service.web.trans.view.goods.SpuView
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
    private val goodsService: SpuService,
    private val skuService: SkuService,
    private val fileService: FileService,
) {
    // 查询所有商品
    @GetMapping
    fun getAllGoods(): List<SpuEntity> {
        return goodsService.fetchAll()
    }

    // 查询区间内的商品
    @GetMapping("/range")
    fun getRangeGoods(
        @RequestParam("pageIndex") pageIndex: Int,
        @RequestParam("pageSize") pageSize: Int
    ): List<SpuEntity> {
        return goodsService.fetchByRange(pageIndex, pageSize)
    }

    // 根据关键字搜索商品
    @GetMapping("/search")
    fun searchGoods(@RequestParam keyword: String): List<SpuEntity> {
        return goodsService.search(keyword)
    }

    // 根据 spuId 获取单个商品
    @GetMapping("/{spuId}")
    fun getGoodsBySpuId(@PathVariable spuId: String): SpuView? {
        return goodsService.fetchBySpuId(spuId)
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
    ): SpuEntity {
        return goodsService.createSpu(
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

    // 根据supId获取sku
    @GetMapping("/{spuId}/skus")
    fun getSkusBySpuId(@PathVariable spuId: String): List<SkuDetail> {
        return skuService.fetchDetails(spuId)
    }


    // 更新商品
    @PutMapping("/{spuId}")
    fun updateGoods(@PathVariable spuId: String, @RequestBody goods: SpuEntity): SpuEntity {
        return goodsService.updateSpu(spuId, goods)
    }

    // 删除商品
    @DeleteMapping("/{spuId}")
    fun deleteGoods(@PathVariable spuId: String) {
        goodsService.deleteById(spuId)
    }
}
