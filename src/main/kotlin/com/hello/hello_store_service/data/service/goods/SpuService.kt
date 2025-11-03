package com.hello.hello_store_service.data.service.goods

import com.hello.hello_store_service.data.model.entity.goods.SpuEntity
import com.hello.hello_store_service.data.model.entity.goods.SpuTag
import com.hello.hello_store_service.data.repository.goods.SkuRepository
import com.hello.hello_store_service.data.repository.goods.SpecRepository
import com.hello.hello_store_service.data.repository.goods.SpuRepository
import com.hello.hello_store_service.data.service.FileService
import com.hello.hello_store_service.web.trans.view.goods.SpuView
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class SpuService(
    private val spuRepository: SpuRepository,
    private val specRepository: SpecRepository,
    private val skuRepository: SkuRepository,
    private val fileService: FileService
) {
    fun search(keyword: String): List<SpuEntity> {
        return spuRepository.findByTitleContaining(keyword)
    }

    fun fetchBySpuIds(spuIds: List<String>): List<SpuEntity> {
        return spuRepository.findAllBySpuIdIn(spuIds)
    }

    fun fetchAll(): List<SpuEntity> {
        return spuRepository.findAll()
    }

    fun fetchByRange(pageIndex: Int, pageSize: Int): List<SpuEntity> {
        return spuRepository.findByRange(pageIndex, pageSize)
    }

    fun fetchBySpuId(supId: String): SpuView? {
        spuRepository.findBySpuId(supId)?.let { spu ->
            val specs = specRepository.findBySpuId(supId)
            val skus = skuRepository.findBySpuId(supId)
            return SpuView.from(spu, skus, specs)
        }
        return null
    }

    fun createSpu(
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
    ): SpuEntity {
        // 上传主图
        val primaryUrl = fileService.uploadFile(primaryImage)

        // 上传轮播图
        val imagesUrl = images.map { fileService.uploadFile(it) }

        // 上传详情图
        val descUrl = desc.map { fileService.uploadFile(it) }

        val goods = SpuEntity(
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

        return spuRepository.save(goods)
    }


    fun updateSpu(
        spuId: String,
        goods: SpuEntity
    ): SpuEntity {
        return spuRepository.updateSpu(spuId, goods)
    }

    fun deleteById(spuId: String) {
        return spuRepository.deleteById(spuId)
    }
}