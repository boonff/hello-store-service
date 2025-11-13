package com.hello.hello_store_service.web.controller


import com.hello.hello_store_service.application.UserService
import com.hello.hello_store_service.data.service.UserDataService
import com.hello.hello_store_service.security.JwtService
import com.hello.hello_store_service.web.request.LoginRequest
import com.hello.hello_store_service.web.request.RegisterRequest
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
    private val userService: UserService, private val jwtService: JwtService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<Map<String, String>> {
        val user = userService.fetchUserEntity(request.username)
        return if (user == null) loginFail()
        else if (user.uid == null) loginFail()
        else if (!userService.validate(user.uid, request.password)) loginFail()
        else loginSuccessful(user.uid, user.userType.name)
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
        // TODO 或许有更好的方法处理异常
        if (user.uid == null) throw IllegalArgumentException("uid怎么为空")
        return ResponseEntity.ok(mapOf("token" to generateToken(user.uid, user.userType.name)))
    }

    @GetMapping("/verify")
    fun verifyToken(): ResponseEntity<Boolean> {
        val authentication = SecurityContextHolder.getContext().authentication

        return if (
            authentication != null
            && authentication.isAuthenticated
            && authentication !is AnonymousAuthenticationToken
        )
            ResponseEntity.ok(true)
        else
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false)
    }


    private fun loginSuccessful(uid: String, roles: String): ResponseEntity<Map<String, String>> {
        val token = jwtService.generateToken(uid, listOf(roles))

        return ResponseEntity.ok(mapOf("token" to token))
    }

    private fun loginFail(): ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mapOf("error" to "用户名或密码错误"))
    }

    private fun generateToken(uid: String, roles: String): String {
        return jwtService.generateToken(uid, listOf(roles))
    }
}
