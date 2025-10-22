package com.hello.hello_store_service.service.address

import com.hello.hello_store_service.model.entity.address.AddressEntity
import com.hello.hello_store_service.repository.address.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService(private val addressRepository: AddressRepository) {
    fun createAddress(address: AddressEntity): AddressEntity {
        return addressRepository.save(address)
    }

    fun userAddress(username: String): List<AddressEntity> {
        return addressRepository.findAllByUsername(username)
    }
}