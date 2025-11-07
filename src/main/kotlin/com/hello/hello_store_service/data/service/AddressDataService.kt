package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.model.entity.address.AddressEntity
import com.hello.hello_store_service.data.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressDataService(private val addressRepository: AddressRepository) {
    fun fetchAddressById(addressId: String): AddressEntity? =
        addressRepository.findById(addressId).orElse(null)

    fun createAddress(address: AddressEntity): AddressEntity {
        return addressRepository.save(address)
    }

    fun userAddress(username: String): List<AddressEntity> {
        return addressRepository.findAllByUsername(username)
    }
}