package com.hello.hello_store_service.data.service.goods

import com.hello.hello_store_service.data.entity.goods.SpuEntity
import com.hello.hello_store_service.data.entity.goods.SpuTag
import com.hello.hello_store_service.data.repository.goods.SkuRepository
import com.hello.hello_store_service.data.repository.goods.SpecRepository
import com.hello.hello_store_service.data.repository.goods.SpuRepository
import com.hello.hello_store_service.data.service.FileDataService
import com.hello.hello_store_service.web.view.goods.SpuView
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class SpuDataService(
    private val spuRepository: SpuRepository,
    private val specRepository: SpecRepository,
    private val skuRepository: SkuRepository,
    private val fileService: FileDataService
) {
    fun search(keyword: String): List<SpuEntity> {
        return spuRepository.findByTitleContaining(keyword)
    }

    fun fetchById(spuId: String): SpuEntity? {
        return spuRepository.findBySpuId(spuId)
    }

    fun fetchByRange(pageIndex: Int, pageSize: Int): List<SpuEntity> {
        return spuRepository.findByRange(pageIndex, pageSize)
    }

}