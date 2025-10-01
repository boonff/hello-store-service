package com.hello.hello_store_service.service.config

import com.hello.hello_store_service.model.entity.config.AppConfig
import com.hello.hello_store_service.model.entity.config.CustomerService
import com.hello.hello_store_service.repository.AppConfigRepository
import org.springframework.stereotype.Service

@Service
class AppConfigService(
    private val repository: AppConfigRepository,
) {
    fun getConfig(): AppConfig? =
        repository.findByConfigId("appConfig")

    fun updateConfig(config: AppConfig): AppConfig =
        repository.save(config.copy(configId = "appConfig"))

    fun initConfig(defaultConfig: AppConfig): AppConfig =
        getConfig() ?: repository.save(defaultConfig.copy(configId = "appConfig"))

    /**
    * 获取客服配置
     */
    fun getCustomerService(): CustomerService? =
        getConfig()?.customerService

    /**
     * 更新客服配置
     */
    fun updateCustomerService(customerService: CustomerService): AppConfig {
        val config = getConfig() ?: AppConfig(customerService = customerService)
        return repository.save(config.copy(customerService = customerService))
    }
}