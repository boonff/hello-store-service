package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.entity.address.LogisticEntity
import com.hello.hello_store_service.data.repository.LogisticRepository
import org.springframework.stereotype.Service

@Service
class LogisticDataService(
    private val logisticRepository: LogisticRepository
) {
    fun fetchById(logisticId: String): LogisticEntity? {
        return logisticRepository.findById(logisticId).orElse(null)
    }

    fun fetchByOrderId(orderId: String): LogisticEntity? {
        return logisticRepository.findByOrderId(orderId)
    }
}