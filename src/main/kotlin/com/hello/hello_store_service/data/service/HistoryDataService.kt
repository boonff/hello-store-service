package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.entity.HistoryEntity
import com.hello.hello_store_service.data.repository.HistoryRepository
import org.springframework.stereotype.Service

@Service
class HistoryDataService(
    private val historyRepository: HistoryRepository
) {
    fun fetchByUid(uid: String): HistoryEntity? {
        return historyRepository.findByUid(uid)
    }

    fun updateHistorySearch(uid: String, historySearch: List<String>): HistoryEntity? {
        val historyEntity = fetchByUid(uid) ?: return null
        return historyRepository.save(
            historyEntity.copy(historySearch = historySearch)
        )
    }
}