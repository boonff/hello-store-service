package com.hello.hello_store_service.service

import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID


@Service
class FileService(private val minioClient: MinioClient) {

    fun uploadFile(file: MultipartFile, bucket: String = "goods"): String {
        val filename = "${UUID.randomUUID()}-${file.originalFilename}"

        minioClient.putObject(
            PutObjectArgs.builder()
                .bucket(bucket)
                .`object`(filename)
                .stream(file.inputStream, file.size, -1) // 输入流, 大小, 默认分片
                .contentType(file.contentType)
                .build()
        )

        return "http://localhost:9000/$bucket/$filename"
    }

    fun deleteFile(bucket: String, objectName: String) {
        minioClient.removeObject(
            RemoveObjectArgs.builder()
                .bucket(bucket)
                .`object`(objectName)
                .build()
        )
    }
}
