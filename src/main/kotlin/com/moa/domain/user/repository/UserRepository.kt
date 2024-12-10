package com.moa.domain.user.repository

import com.moa.domain.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByDodamId(dodamId: String): User?
}