package com.hello.hello_store_service.data.repository.user

import com.hello.hello_store_service.data.entity.user.UserCredentialEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserCredentialRepository : MongoRepository<UserCredentialEntity, String> {

}