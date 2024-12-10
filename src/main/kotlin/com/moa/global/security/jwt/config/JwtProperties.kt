package com.moa.global.security.jwt.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val header: String,
    val issuer: String,
    val prefix: String,
    val secretKey: String,
    val accessTokenExpiration: Long,
    val refreshTokenExpiration: Long
)