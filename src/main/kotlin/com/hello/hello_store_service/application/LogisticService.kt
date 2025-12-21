package com.hello.hello_store_service.application

import com.hello.hello_store_service.data.entity.address.LogisticEntity
import com.hello.hello_store_service.data.service.LogisticDataService
import org.springframework.stereotype.Service

@Service
class LogisticService(
    private val logisticDataService: LogisticDataService
) {
    fun fetchById(orderId: String): LogisticEntity? {
        return logisticDataService.fetchById(orderId)
    }
}
