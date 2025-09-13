package com.hello.hello_store_service

import com.hello.hello_store_service.config.MinioProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(MinioProperties::class)
@SpringBootApplication
class HelloStoreServiceApplication

fun main(args: Array<String>) {
	runApplication<HelloStoreServiceApplication>(*args)
}
