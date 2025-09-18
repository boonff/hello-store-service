package com.hello.hello_store_service.service

import com.hello.hello_store_service.model.entity.User
import com.hello.hello_store_service.model.entity.UserType

interface UserService {
    fun findByUsername(username: String): User?
    fun register(
        phoneNumber: String,
        username: String,
        nickName: String,
        password: String,
        userType: UserType
    ): User

    fun validateLogin(username: String, password: String): Boolean
}