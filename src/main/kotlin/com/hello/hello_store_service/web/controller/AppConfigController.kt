package com.hello.hello_store_service.web.controller


import com.hello.hello_store_service.data.model.entity.AppConfigEntity
import com.hello.hello_store_service.data.model.entity.CustomerService
import com.hello.hello_store_service.data.service.AppConfigDataService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/config")
class AppConfigController(
    private val appConfigService: AppConfigDataService
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


//    @PostMapping("/customerService")
//    fun updateCustomerService(@RequestParam config: CustomerService): AppConfigEntity =
//        appConfigService.updateCustomerService(config)
}
