package com.hello.hello_store_service.web.request

import com.hello.hello_store_service.data.entity.address.AddressEntity

data class AddressRequest(
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
    val addressTag: String?,
    val latitude: Float? = null,
    val longitude: Float? = null,
) {
    fun toEntity(username: String): AddressEntity {
        return AddressEntity(
            addressId = null,
            username = username,
            cityCode = cityCode,
            cityName = cityName,
            countryCode = countryCode,
            countryName = countryName,
            detailAddress = detailAddress,
            districtCode = districtCode,
            districtName = districtName,
            isDefault = isDefault,
            isOrderSure = isOrderSure,
            name = name,
            phone = phone,
            provinceCode = provinceCode,
            provinceName = provinceName,
            addressTag = addressTag,
            latitude = latitude,
            longitude = longitude
        )
    }
}