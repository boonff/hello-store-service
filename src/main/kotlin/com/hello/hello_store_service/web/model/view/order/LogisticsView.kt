package com.hello.hello_store_service.web.model.view.order

data class LogisticsView(
    val logisticsType: Int,           // 物流类型，例如 1 表示快递
    val logisticsNo: String,          // 物流单号
    val logisticsStatus: String,      // 物流状态
    val logisticsCompanyCode: String, // 物流公司编码
    val logisticsCompanyName: String, // 物流公司名称
    val receiverAddressId: String,    // 收货地址ID
    val provinceCode: String,         // 收货省份编码
    val cityCode: String,             // 收货城市编码
    val countryCode: String,          // 收货区/县编码
    val receiverProvince: String,     // 收货省份名称
    val receiverCity: String,         // 收货城市名称
    val receiverCountry: String,      // 收货区/县名称
    val receiverArea: String,         // 收货街道/区名称
    val receiverAddress: String,      // 详细收货地址
    val receiverPostCode: String,     // 邮政编码
    val receiverLongitude: String,    // 收货地址经度
    val receiverLatitude: String,     // 收货地址纬度
    val receiverIdentity: String,     // 收货人身份标识
    val receiverPhone: String,        // 收货人电话
    val receiverName: String,         // 收货人姓名
    val expectArrivalTime: String,    // 预计到达时间
    val senderName: String,           // 发货人姓名
    val senderPhone: String,          // 发货人电话
    val senderAddress: String,        // 发货人地址
    val sendTime: String,             // 发货时间
    val arrivalTime: String           // 实际到达时间
)
