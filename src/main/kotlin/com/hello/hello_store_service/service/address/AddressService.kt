package com.hello.hello_store_service.service.address

import com.hello.hello_store_service.model.entity.address.Address
import com.hello.hello_store_service.repository.address.AddressRepository
import com.hello.hello_store_service.util.SecurityUtils
import org.springframework.stereotype.Service

@Service
class AddressService(private val addressRepository: AddressRepository) {
    fun createAddress(address: Address): Address {
        return addressRepository.save(address)
    }

    fun userAddress(username: String): List<Address> {
        return addressRepository.findAllByUsername(username)
    }
}