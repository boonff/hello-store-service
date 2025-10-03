package com.hello.hello_store_service.service.goods

import com.hello.hello_store_service.model.entity.goods.Spu
import com.hello.hello_store_service.model.entity.goods.SpuTag
import com.hello.hello_store_service.repository.goods.SpuRepository
import com.hello.hello_store_service.service.file.FileService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class GoodsService(
    private val goodsRepository: SpuRepository,
    private val fileService: FileService
) {
    fun search(keyword: String): List<Spu> {
        return goodsRepository.findByTitleContaining(keyword)
    }
    fun fetchBySpuIds(spuIds: List<String>): List<Spu> {
        return goodsRepository.findAllBySpuIdIn(spuIds)
    }

    fun fetchAllGoods(): List<Spu> {
        return goodsRepository.findAll()
    }

    fun fetchByRange(pageIndex: Int, pageSize: Int): List<Spu> {
        return goodsRepository.findByRange(pageIndex, pageSize)
    }

    fun fetchBySpuId(supId: String): Spu? {
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
    ): Spu {
        // 上传主图
        val primaryUrl = fileService.uploadFile(primaryImage)

        // 上传轮播图
        val imagesUrl = images.map { fileService.uploadFile(it) }

        // 上传详情图
        val descUrl = desc.map { fileService.uploadFile(it) }

        val goods = Spu(
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
        )

        return goodsRepository.save(goods)
    }


     fun updateGoods(
        spuId: String,
        goods: Spu
    ): Spu {
        return goodsRepository.updateSpu(spuId, goods)
    }

     fun deleteGoods(spuId: String) {
        return goodsRepository.deleteById(spuId)
    }
}