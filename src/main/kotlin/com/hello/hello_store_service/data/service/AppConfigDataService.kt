package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.entity.AppCONFIG
import com.hello.hello_store_service.data.entity.AppConfigEntity
import com.hello.hello_store_service.data.entity.CustomerService
import com.hello.hello_store_service.data.repository.AppConfigRepository
import org.springframework.stereotype.Service

@Service
class AppConfigDataService(
    private val repository: AppConfigRepository,
) {
    fun fetchConfig(): AppConfigEntity? =
        repository.findByConfigId(AppCONFIG)

    fun updateConfig(config: AppConfigEntity): AppConfigEntity =
        repository.save(config.copy(configId = AppCONFIG))

    fun initConfig(defaultConfig: AppConfigEntity): AppConfigEntity =
        fetchConfig() ?: repository.save(defaultConfig.copy(configId = AppCONFIG))

    /**
    * 获取客服配置
     */
    fun getCustomerService(): CustomerService? =
        fetchConfig()?.customerService
}