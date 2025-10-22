package com.hello.hello_store_service.controller

import com.hello.hello_store_service.model.transfer.user.LoginRequest
import com.hello.hello_store_service.model.transfer.user.RegisterRequest
import com.hello.hello_store_service.security.JwtService
import com.hello.hello_store_service.service.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userService: UserService,
    private val jwtService: JwtService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Map<String, String>> {
        val user = userService.getUser(request.username)
        return if (user != null && userService.validateLogin(request.username, request.password)) {
            val token = jwtService.generateToken(user.username, listOf(user.userType.name))

            ResponseEntity.ok(mapOf("token" to token))
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(mapOf("error" to "用户名或密码错误"))
        }
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<Map<String, String>> {
        val user = userService.register(
            phoneNumber = request.phoneNumber,
            username = request.username,
            nickName = request.nickName,
            password = request.password,
            userType = request.userType
        )

        val token = jwtService.generateToken(user.username, listOf(user.userType.name))

        return ResponseEntity.ok(mapOf("token" to token))
    }

    @GetMapping("/verify")
    fun verifyToken(): ResponseEntity<Boolean> {
        val authentication = SecurityContextHolder.getContext().authentication

        val isValid = authentication != null &&
                authentication.isAuthenticated &&
                authentication !is AnonymousAuthenticationToken

        return if (isValid) {
            ResponseEntity.ok(true)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false)
        }
    }


}
