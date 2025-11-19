package com.hello.hello_store_service.application.history

import com.hello.hello_store_service.data.service.HistoryDataService
import org.springframework.stereotype.Service

@Service
class HistorySearchService(
    private val historyDataService: HistoryDataService
) {
    fun fetchHistorySearch(uid: String): List<String> {
        val historyEntity = historyDataService.fetchByUid(uid) ?: return emptyList()
        return historyEntity.historySearch
    }

    fun addHistorySearch(uid: String, searchKey: String): Boolean {
        val historySearch = historyDataService.fetchByUid(uid)?.historySearch ?: emptyList()

        return historyDataService.updateHistorySearch(
            uid,
            processHistory(historySearch, searchKey)
        ) != null
    }

    private fun processHistory(
        historySearch: List<String>,
        searchKey: String
    ): List<String> {
        return listOf(searchKey) + historySearch
            .filter { it != searchKey }
            .take(19)
    }

}