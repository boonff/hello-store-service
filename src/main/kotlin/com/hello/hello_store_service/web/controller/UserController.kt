package com.hello.hello_store_service.web.controller

import com.hello.hello_store_service.config.MinioProperties
import com.hello.hello_store_service.data.service.FileDataService
import com.hello.hello_store_service.data.service.UserDataService
import com.hello.hello_store_service.security.SecurityUtils
import com.hello.hello_store_service.web.model.view.UserView
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserDataService,
    private val fileService: FileDataService,
    private val minioProperties: MinioProperties
) {

    // 获取当前登录用户信息
    @GetMapping("/info")
    fun getUserInfo(): ResponseEntity<UserView> {
        val authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name

        val user = userService.getUser(username)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(UserView.from(user))
    }

    // 上传用户头像
    @PostMapping("/avatar")
    fun uploadAvatar(@RequestPart("file") file: MultipartFile): ResponseEntity<Map<String, String>> {
        val authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name

        val avatarUrl = fileService.uploadFile(file, bucket = minioProperties.bucket)

        userService.updateAvatar(username, avatarUrl)

        return ResponseEntity.ok(mapOf("url" to avatarUrl))
    }

    //修改昵称
    @PostMapping("/nickName")
    fun updateNickName(@RequestBody nickName: String) {
        val username = SecurityUtils.currentUsername()
        userService.updateNickName(username, nickName)
    }

    //修改性别
    data class GenderRequest(val gender: Int)
    @PostMapping("/gender")
    fun updateGender(@RequestBody request: GenderRequest) {
        val username = SecurityUtils.currentUsername()
        userService.updateGender(username, request.gender)
    }
}
