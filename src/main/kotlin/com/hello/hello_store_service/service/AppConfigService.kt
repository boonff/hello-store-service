package com.hello.hello_store_service.service

import com.hello.hello_store_service.model.entity.AppConfig
import com.hello.hello_store_service.repository.AppConfigRepository
import org.springframework.stereotype.Service

@Service
class AppConfigService(
    private val repository: AppConfigRepository
) {
    fun getConfig(): AppConfig? =
        repository.findById("appConfig").orElse(null)

    fun updateConfig(config: AppConfig): AppConfig =
        repository.save(config.copy(id = "appConfig"))

    fun initConfig(defaultConfig: AppConfig): AppConfig =
        getConfig() ?: repository.save(defaultConfig.copy(id = "appConfig"))
}
