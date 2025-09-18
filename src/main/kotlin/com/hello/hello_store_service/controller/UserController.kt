package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.dto.UserDto
import com.hello.hello_store_service.service.UserService
import com.hello.hello_store_service.security.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val jwtService: JwtService
) {

    // 获取当前登录用户信息
    @GetMapping("/info")
    fun getUserInfo(): ResponseEntity<UserDto> {
        val authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name

        val user = userService.findByUsername(username)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(UserDto.from(user))
    }
}
