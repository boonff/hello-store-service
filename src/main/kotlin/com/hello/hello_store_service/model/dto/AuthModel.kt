package com.hello.hello_store_service.model.dto

import com.hello.hello_store_service.model.entity.UserType

data class LoginRequest(
    val username: String,
    val password: String
)

data class RegisterRequest(
    val username: String,
    val nickName: String,
    val password: String,
    val avatarUrl: String? = null,
    val phoneNumber: String,
    val gender: Int = 0,
    val userType: UserType
)
