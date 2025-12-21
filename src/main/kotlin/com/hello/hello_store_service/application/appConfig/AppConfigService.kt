package com.hello.hello_store_service.application.appConfig

import com.hello.hello_store_service.data.entity.AppConfigEntity
import com.hello.hello_store_service.data.entity.PaymentInfo
import com.hello.hello_store_service.data.service.AppConfigDataService
import org.springframework.stereotype.Service

@Service
class AppConfigService(
    private val appConfigDataService: AppConfigDataService
) {
    fun fetchPaymentInfo(): PaymentInfo? {
        return fetchAppConfig()?.paymentInfo
    }


    private fun fetchAppConfig(): AppConfigEntity? {
        return appConfigDataService.fetchConfig()
    }
}