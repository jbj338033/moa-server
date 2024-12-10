package com.moa.global.security.holder

import com.moa.domain.user.domain.entity.User
import com.moa.domain.user.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityHolder(
    private val userRepository: UserRepository
) {
    val user: User
        get() = userRepository.findByDodamId(SecurityContextHolder.getContext().authentication.name) ?: throw IllegalArgumentException("Invalid token")
}