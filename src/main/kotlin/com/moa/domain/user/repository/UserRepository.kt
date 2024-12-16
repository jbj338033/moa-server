package com.moa.domain.user.repository

import com.moa.domain.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<User, UUID> {
    fun findByDodamId(dodamId: String): User?
}