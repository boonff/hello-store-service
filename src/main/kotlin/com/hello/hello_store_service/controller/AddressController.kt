package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.dto.address.AddressDTO
import com.hello.hello_store_service.model.entity.address.Address
import com.hello.hello_store_service.service.address.AddressService
import com.hello.hello_store_service.util.SecurityUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/address")
class AddressController(
    private val addressService: AddressService
) {
    @PostMapping("/create")
    fun createAddress(@RequestBody address: AddressDTO) {
        val username = SecurityUtils.currentUsername()
        addressService.createAddress(address.toEntity(username))
    }

    @GetMapping
    fun userAddress(): List<Address> {
        val username = SecurityUtils.currentUsername()
        return addressService.userAddress(username)
    }


}