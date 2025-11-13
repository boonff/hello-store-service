package com.hello.hello_store_service.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtService(
    @param:Value("\${jwt.secret}") private val secret: String,          // 从 properties 读取 secret
    @param:Value("\${jwt.expiration-millis:86400000}") private val expirationMillis: Long // 默认 1 天
) {

    private lateinit var secretKey: SecretKey

    @PostConstruct
    fun init() {
        // 用 secret 创建 SecretKey
        secretKey = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun generateToken(uid: String, roles: List<String>): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationMillis)
        return Jwts.builder()
            .setSubject(uid)
            .claim("roles", roles)   // 存储角色集合
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getUidFromToken(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    fun getRolesFromToken(token: String): List<String> {
        val claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
        val roles = claims["roles"]
        return if (roles is List<*>) roles.filterIsInstance<String>() else emptyList()
    }
}
