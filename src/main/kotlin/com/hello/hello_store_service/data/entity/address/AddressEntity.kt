package com.hello.hello_store_service.data.entity.address

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("address")
data class AddressEntity(
    @Id val addressId: String? = null,
    val username: String,
    val cityCode: String,
    val cityName: String,
    val countryCode: String,
    val countryName: String,
    val detailAddress: String,
    val districtCode: String,
    val districtName: String,
    val isDefault: Boolean,
    val isOrderSure: Boolean,
    val name: String,
    val phone: String,
    val provinceCode: String,
    val provinceName: String,
    val addressTag: String? = null,
    val latitude: Float? = null,
    val longitude: Float? = null,
)