package com.hello.hello_store_service.application

import com.hello.hello_store_service.data.entity.user.Gender
import com.hello.hello_store_service.data.entity.user.UserCredentialEntity
import com.hello.hello_store_service.data.entity.user.UserEntity
import com.hello.hello_store_service.data.entity.user.UserType
import com.hello.hello_store_service.data.service.UserDataService
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userDataService: UserDataService
) {
    // TODO 或许有更好的方法处理异常
    fun register(
        phoneNumber: String,
        username: String,
        nickName: String,
        password: String,
        userType: UserType
    ): UserEntity {
        if (userDataService.fetchUserEntity(username) != null) throw IllegalArgumentException("用户名已存在")

        val newUser = UserEntity(
            phoneNumber = phoneNumber,
            username = username,
            nickName = nickName,
            gender = Gender.Female,//TODO 性别
            avatarUrl = "",
            userType = userType,
        )

        val userEntity = saveUserEntity(newUser) ?: throw IllegalArgumentException("账户保存失败")
        if (userEntity.uid == null) throw IllegalArgumentException("uid怎么为空")

        saveUserCredential(
            genCredential(userEntity.uid, password)
        )
        return userEntity
    }

    fun validate(uid: String, password: String): Boolean {
        val credential = userDataService.fetchCredential(uid) ?: return false
        return BCrypt.checkpw(password, credential.passwordHash)
    }

    fun fetchUserEntity(username: String): UserEntity? {
        return userDataService.fetchUserEntity(username)
    }

    private fun genCredential(uid: String, password: String): UserCredentialEntity {
        val salt = BCrypt.gensalt()
        val passwordHash = BCrypt.hashpw(password, salt)

        return UserCredentialEntity(
            uid = uid,
            passwordHash = passwordHash,
            salt = salt
        )
    }

    private fun saveUserEntity(usrEntity: UserEntity): UserEntity? {
        return userDataService.saveUserEntity(usrEntity)
    }

    private fun saveUserCredential(userCredentialEntity: UserCredentialEntity): UserCredentialEntity? {
        return userDataService.saveUserCredential(userCredentialEntity)
    }

}