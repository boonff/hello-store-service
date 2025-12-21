package com.hello.hello_store_service.web.view.aftersales

data class LogisticsView(
    val logisticsType: Int,
    val logisticsNo: String,
    val logisticsStatus: String?,
    val logisticsCompanyCode: String,
    val logisticsCompanyName: String,
    val receiverAddressId: String,
    val provinceCode: String,
    val cityCode: String,
    val countryCode: String,
    val receiverProvince: String,
    val receiverCity: String,
    val receiverCountry: String,
    val receiverArea: String,
    val receiverAddress: String,
    val receiverPostCode: String,
    val receiverLongitude: String,
    val receiverLatitude: String,
    val receiverIdentity: String,
    val receiverPhone: String,
    val receiverName: String,
    val expectArrivalTime: String?,
    val senderName: String,
    val senderPhone: String,
    val senderAddress: String,
    val sendTime: String?,
    val arrivalTime: String?,
    val nodes: List<LogisticsNode>
)

data class LogisticsNode(
    val title: String,
    val icon: String,
    val code: String,
    val desc: String,
    val date: String
)
