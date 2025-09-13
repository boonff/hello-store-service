package com.hello.hello_store_service.config


import io.minio.MinioClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@ConfigurationProperties("minio")
data class MinioProperties @ConstructorBinding constructor(
    val url: String,
    val accessKey: String,
    val secretKey: String,
    val bucket: String
)


@Configuration
@EnableConfigurationProperties(MinioProperties::class)
class MinioConfig(private val properties: MinioProperties) {

    @Bean
    fun minioClient(): MinioClient {
        return MinioClient.builder()
            .endpoint(properties.url)
            .credentials(properties.accessKey, properties.secretKey)
            .build()
    }
}