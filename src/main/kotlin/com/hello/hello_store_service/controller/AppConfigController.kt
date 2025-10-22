package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.entity.config.AppConfigEntity
import com.hello.hello_store_service.model.entity.config.CustomerService
import com.hello.hello_store_service.service.config.AppConfigService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/config")
class AppConfigController(
    private val appConfigService: AppConfigService
) {
    @GetMapping
    fun getConfig(): AppConfigEntity? =
        appConfigService.getConfig()

    @PutMapping
    fun updateConfig(@RequestBody config: AppConfigEntity): AppConfigEntity =
        appConfigService.updateConfig(config)

    @PostMapping("/init")
    fun initConfig(@RequestBody defaultConfig: AppConfigEntity): AppConfigEntity =
        appConfigService.initConfig(defaultConfig)

    @GetMapping("/customerService")
    fun getCustomerService(): CustomerService? =
        appConfigService.getCustomerService()


    @PostMapping("/customerService")
    fun updateCustomerService(@RequestParam config: CustomerService): AppConfigEntity =
        appConfigService.updateCustomerService(config)
}
