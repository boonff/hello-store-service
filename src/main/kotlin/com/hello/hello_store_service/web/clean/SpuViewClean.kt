package com.hello.hello_store_service.web.clean

import com.hello.hello_store_service.application.SpuService
import com.hello.hello_store_service.data.repository.goods.SkuRepository
import com.hello.hello_store_service.data.repository.goods.SpecRepository
import com.hello.hello_store_service.web.view.goods.SpuView
import org.springframework.stereotype.Service

@Service
class SpuViewClean(
    private val specRepository: SpecRepository,
    private val skuRepository: SkuRepository,
    private val spuService: SpuService
) {
    fun fetchSpuView(supId: String): SpuView? {
        val spuEntity = spuService.fetchSpu(supId) ?: return null

        val specs = specRepository.findBySpuId(supId)
        val skus = skuRepository.findBySpuId(supId)
        return SpuView.from(spuEntity, skus, specs)
    }
}