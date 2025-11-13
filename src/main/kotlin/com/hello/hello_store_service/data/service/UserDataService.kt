package com.hello.hello_store_service.data.service

import com.hello.hello_store_service.data.entity.user.UserCredentialEntity
import com.hello.hello_store_service.data.entity.user.UserEntity
import com.hello.hello_store_service.data.repository.user.UserCredentialRepository
import com.hello.hello_store_service.data.repository.user.UserRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class UserDataService(
    private val userRepository: UserRepository,
    private val userCredentialRepository: UserCredentialRepository,
    private val mongoTemplate: MongoTemplate
) {
    fun fetchUserEntity(username: String): UserEntity? {
        return userRepository.findByUsername(username)
    }

    fun fetchCredential(uid: String): UserCredentialEntity? {
        return userCredentialRepository.findById(uid).orElse(null)
    }

    fun saveUserEntity(userEntity: UserEntity): UserEntity? {
        return userRepository.save(userEntity)
    }

    fun saveUserCredential(userCredentialEntity: UserCredentialEntity): UserCredentialEntity? {
        return userCredentialRepository.save(userCredentialEntity)
    }

    fun fetchUserEntityByUid(uid: String): UserEntity? {
        return userRepository.findById(uid).orElse(null)
    }

    //TODO应该移动到repository
    fun updateAvatar(uid: String, newAvatarUrl: String) {
        val query = Query(Criteria.where("uid").`is`(uid))
        val update = Update().set("avatarUrl", newAvatarUrl).set("updatedAt", Instant.now())
        mongoTemplate.updateFirst(query, update, UserEntity::class.java)
    }

    fun updateNickName(uid: String, newNickName: String) {
        val query = Query(Criteria.where("uid").`is`(uid))
        val update = Update().set("nickName", newNickName).set("updatedAt", Instant.now())
        mongoTemplate.updateFirst(query, update, UserEntity::class.java)
    }

    fun updateGender(uid: String, newGender: Int) {
        val query = Query(Criteria.where("uid").`is`(uid))

        val update = Update().set("gender", newGender).set("updatedAt", Instant.now())
        mongoTemplate.updateFirst(query, update, UserEntity::class.java)
    }

}