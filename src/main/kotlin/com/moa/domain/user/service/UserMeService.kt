package com.moa.domain.user.service

import com.moa.domain.user.dto.response.UserMeResponse
import com.moa.global.security.holder.SecurityHolder
import org.springframework.stereotype.Service

@Service
class UserMeService(private val securityHolder: SecurityHolder) {
    fun getMe(): UserMeResponse {
        val user = securityHolder.user

        return UserMeResponse.of(user)
    }
}