package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.data.model.entity.address.AddressEntity
import com.hello.hello_store_service.data.service.AddressService
import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.trans.param.AddressRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/address")
class AddressController(
    private val addressService: AddressService
) {
    @PostMapping("/create")
    fun createAddress(@RequestBody address: AddressRequest) {
        val username = SecurityUtils.currentUsername()
        addressService.createAddress(address.toEntity(username))
    }

    @GetMapping
    fun userAddress(): List<AddressEntity> {
        val username = SecurityUtils.currentUsername()
        return addressService.userAddress(username)
    }


}