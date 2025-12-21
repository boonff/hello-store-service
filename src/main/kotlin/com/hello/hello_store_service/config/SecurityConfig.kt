package com.hello.hello_store_service.config

import com.hello.hello_store_service.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity, jwtFilter: JwtAuthenticationFilter): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(
                    "/auth/**",
                    "/goods/**",
                    "/comments/**",
                    "/api/config/**",
                    "/api/config/customerService"
                ).permitAll() // 登录注册放行
                    .anyRequest().authenticated() // 其他接口需要认证
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }


}