package com.hello.hello_store_service.application

import com.hello.hello_store_service.application.history.HistorySearchService
import com.hello.hello_store_service.data.entity.goods.SpuEntity
import com.hello.hello_store_service.data.service.goods.SpuDataService
import org.springframework.stereotype.Service

@Service
class SpuService(
    private val spuDataService: SpuDataService,
    private val historySearchService: HistorySearchService
) {
    fun search(uid: String, keyword: String): List<SpuEntity> {
        historySearchService.addHistorySearch(uid, keyword)
        return spuDataService.search(keyword)
    }

    fun fetchSpu(spuId: String): SpuEntity? {
        return spuDataService.fetchById(spuId)
    }

    fun fetchByRange(pageIndex: Int, pageSize: Int): List<SpuEntity> {
        return spuDataService.fetchByRange(pageIndex, pageSize)
    }
}