package com.moa.global.security.details

import com.moa.domain.user.domain.entity.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    val user: User
): UserDetails {
    override fun getUsername() = user.dodamId
    override fun getPassword() = null
    override fun getAuthorities() = listOf(SimpleGrantedAuthority(user.role.name))
}