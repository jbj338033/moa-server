package com.moa.domain.auth.dto.response

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
)
