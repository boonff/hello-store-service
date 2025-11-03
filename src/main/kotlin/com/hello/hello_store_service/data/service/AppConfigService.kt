package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.model.entity.AppConfigEntity
import com.hello.hello_store_service.data.model.entity.CustomerService
import com.hello.hello_store_service.data.repository.AppConfigRepository
import org.springframework.stereotype.Service

@Service
class AppConfigService(
    private val repository: AppConfigRepository,
) {
    fun getConfig(): AppConfigEntity? =
        repository.findByConfigId("appConfig")

    fun updateConfig(config: AppConfigEntity): AppConfigEntity =
        repository.save(config.copy(configId = "appConfig"))

    fun initConfig(defaultConfig: AppConfigEntity): AppConfigEntity =
        getConfig() ?: repository.save(defaultConfig.copy(configId = "appConfig"))

    /**
    * 获取客服配置
     */
    fun getCustomerService(): CustomerService? =
        getConfig()?.customerService

    /**
     * 更新客服配置
     */
    fun updateCustomerService(customerService: CustomerService): AppConfigEntity {
        val config = getConfig() ?: AppConfigEntity(customerService = customerService)
        return repository.save(config.copy(customerService = customerService))
    }
}