package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.entity.AppConfig
import com.hello.hello_store_service.service.AppConfigService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/config")
class AppConfigController(
    private val appConfigService: AppConfigService
) {
    @GetMapping
    fun getConfig(): AppConfig? =
        appConfigService.getConfig()

    @PutMapping
    fun updateConfig(@RequestBody config: AppConfig): AppConfig =
        appConfigService.updateConfig(config)

    @PostMapping("/init")
    fun initConfig(@RequestBody defaultConfig: AppConfig): AppConfig =
        appConfigService.initConfig(defaultConfig)
}
