package com.hello.hello_store_service.service

import com.hello.hello_store_service.model.entity.User
import com.hello.hello_store_service.model.entity.UserCredential
import com.hello.hello_store_service.model.entity.UserType
import com.hello.hello_store_service.repository.UserCredentialRepository
import com.hello.hello_store_service.repository.UserRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userCredentialRepository: UserCredentialRepository,
    private val mongoTemplate: MongoTemplate
) {
    fun getUser(username: String): User? {
        return userRepository.findByUsername(username)
    }

    // 注册用户
    fun register(
        phoneNumber: String,
        username: String,
        nickName: String,
        password: String,
        userType: UserType
    ): User {
        if (userRepository.findByUsername(username) != null) {
            throw IllegalArgumentException("用户名已存在")
        }

        // 先创建用户基础信息
        val user = User(
            phoneNumber = phoneNumber,
            username = username,
            nickName = nickName,
            gender = 0,
            avatarUrl = "",
            userType = userType,
        )
        val savedUser = userRepository.save(user)

        // 创建用户凭证
        val salt = BCrypt.gensalt()
        val passwordHash = BCrypt.hashpw(password, salt)

        val credential = UserCredential(
            userId = savedUser.id!!,   // 使用用户 ID 作为关联
            passwordHash = passwordHash,
            salt = salt
        )
        userCredentialRepository.save(credential)

        return savedUser
    }

    fun validateLogin(username: String, password: String): Boolean {
        val user = userRepository.findByUsername(username) ?: return false

        val credential = userCredentialRepository.findByUserId(user.id!!) ?: return false

        return BCrypt.checkpw(password, credential.passwordHash)
    }

    fun updateAvatar(username: String, newAvatarUrl: String) {
        val query = Query(Criteria.where("username").`is`(username))
        val update = Update()
            .set("avatarUrl", newAvatarUrl)
            .set("updatedAt", Instant.now())
        mongoTemplate.updateFirst(query, update, User::class.java)
    }

    fun updateNickName(username: String, newNickName: String) {
        val query = Query(Criteria.where("username").`is`(username))
        val update = Update()
            .set("nickName", newNickName)
            .set("updatedAt", Instant.now())
        mongoTemplate.updateFirst(query, update, User::class.java)
    }

    fun updateGender(username: String, newGender: Int) {
        val query = Query(Criteria.where("username").`is`(username))

        val update = Update()
            .set("gender", newGender)
            .set("updatedAt", Instant.now())
        mongoTemplate.updateFirst(query, update, User::class.java)
    }
}