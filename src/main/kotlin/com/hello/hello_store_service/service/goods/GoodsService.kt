package com.hello.hello_store_service.service.goods

import com.hello.hello_store_service.model.entity.goods.Goods
import com.hello.hello_store_service.model.entity.goods.SpuTag
import com.hello.hello_store_service.repository.goods.GoodsRepository
import com.hello.hello_store_service.service.file.FileService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class GoodsService(
    private val goodsRepository: GoodsRepository,
    private val fileService: FileService
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

    fun createGoods(
        saasId: String,
        storeId: String,
        title: String,
        etitle: String,
        primaryImage: MultipartFile,
        images: List<MultipartFile>,
        desc: List<MultipartFile>,
        spuTagList: List<SpuTag> = emptyList(),
        categoryIds: List<String> = emptyList(),
        groupIdList: List<String> = emptyList(),
        isPutOnSale: Int = 1
    ): Goods {
        // 上传主图
        val primaryUrl = fileService.uploadFile(primaryImage)

        // 上传轮播图
        val imagesUrl = images.map { fileService.uploadFile(it) }

        // 上传详情图
        val descUrl = desc.map { fileService.uploadFile(it) }

        val goods = Goods(
            saasId = saasId,
            storeId = storeId,
            spuId = "0", // 可在创建时生成唯一 ID
            title = title,
            etitle = etitle,
            primaryImage = primaryUrl,
            images = imagesUrl,
            desc = descUrl,
            spuTagList = spuTagList,
            categoryIds = categoryIds,
            groupIdList = groupIdList,
            isPutOnSale = isPutOnSale
        )

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