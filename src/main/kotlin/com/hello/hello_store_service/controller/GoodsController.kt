package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.Goods
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
        @RequestParam("pageIndex") pageIndex:Int,
        @RequestParam("pageSize") pageSize:Int
    ):List<Goods>{
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

    // 新增商品
    @PostMapping("/create")
    fun createGoods(
        @RequestParam("title") title: String,
        @RequestParam("primaryImage") primaryImage: MultipartFile,
        @RequestParam("images") images: List<MultipartFile>,
        @RequestParam("desc") desc: List<MultipartFile>
    ): Goods {
        // 上传主图
        val primaryUrl = fileService.uploadFile(primaryImage)

        // 上传轮播图
        val imagesUrl = images.map { fileService.uploadFile(it) }

        // 上传详情图
        val descUrl = desc.map { fileService.uploadFile(it) }

        val goods = Goods(
            saasId = "88888888",
            storeId = "1000",
            spuId = "0",
            title = title,
            primaryImage = primaryUrl,
            images = imagesUrl,
            video = null,
            minSalePrice = 1000,
            minLinePrice = 1200,
            maxSalePrice = 2000,
            maxLinePrice = 2200,
            spuStockQuantity = 100,
            soldNum = 0,
            isPutOnSale = 1,
            specList = emptyList(),
            skuList = emptyList(),
            spuTagList = emptyList(),
            limitInfo = null,
            desc = descUrl,
            etitle = ""
        )

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
