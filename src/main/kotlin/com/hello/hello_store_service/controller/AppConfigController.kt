package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.entity.config.AppConfig
import com.hello.hello_store_service.model.entity.config.CustomerService
import com.hello.hello_store_service.service.config.AppConfigService
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

    @GetMapping("/customerService")
    fun getCustomerService(): CustomerService? =
        appConfigService.getCustomerService()


    @PostMapping("/customerService")
    fun updateCustomerService(@RequestParam config: CustomerService): AppConfig =
        appConfigService.updateCustomerService(config)
}
